package com.playpro.playpro.facility.service.impl;

import com.playpro.playpro.facility.client.OrderClient;
import com.playpro.playpro.facility.client.dto.OrderClientDto;
import com.playpro.playpro.facility.client.dto.OrderItemClientDto;
import com.playpro.playpro.facility.client.dto.OrderSearchClientRequest;
import com.playpro.playpro.facility.dto.BulkInventorySummaryRequest;
import com.playpro.playpro.facility.dto.FacilityInventoryBreakdownDto;
import com.playpro.playpro.facility.dto.ProductInventoryDetailDto;
import com.playpro.playpro.facility.dto.ProductInventorySummaryDto;
import com.playpro.playpro.facility.dto.ProductPurchaseOrderLineDto;
import com.playpro.playpro.facility.entity.Facility;
import com.playpro.playpro.facility.entity.InventoryItem;
import com.playpro.playpro.facility.entity.ProductStoreFacility;
import com.playpro.playpro.facility.repository.FacilityRepository;
import com.playpro.playpro.facility.repository.InventoryItemRepository;
import com.playpro.playpro.facility.repository.ProductStoreFacilityRepository;
import com.playpro.playpro.facility.repository.ShipmentReceiptRepository;
import com.playpro.playpro.facility.service.InventoryQueryService;
import com.playpro.playpro.facility.util.InventoryTotals;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class InventoryQueryServiceImpl implements InventoryQueryService {

    private static final String PURCHASE_ORDER = "PURCHASE_ORDER";

    private final InventoryItemRepository inventoryItemRepository;
    private final ProductStoreFacilityRepository productStoreFacilityRepository;
    private final FacilityRepository facilityRepository;
    private final ShipmentReceiptRepository shipmentReceiptRepository;
    private final OrderClient orderClient;

    public InventoryQueryServiceImpl(InventoryItemRepository inventoryItemRepository,
                                     ProductStoreFacilityRepository productStoreFacilityRepository,
                                     FacilityRepository facilityRepository,
                                     ShipmentReceiptRepository shipmentReceiptRepository,
                                     OrderClient orderClient) {
        this.inventoryItemRepository = inventoryItemRepository;
        this.productStoreFacilityRepository = productStoreFacilityRepository;
        this.facilityRepository = facilityRepository;
        this.shipmentReceiptRepository = shipmentReceiptRepository;
        this.orderClient = orderClient;
    }

    @Override
    public ProductInventoryDetailDto getProductInventory(String productId, String productStoreId, String xUser) {
        List<String> facilityIds = resolveFacilityIds(productStoreId);
        List<InventoryItem> items = loadItems(facilityIds, productId);

        ProductInventoryDetailDto detail = new ProductInventoryDetailDto();
        detail.setSummary(buildSummary(productId, productStoreId, items));
        detail.setFacilities(buildFacilityBreakdown(items));
        detail.setPurchaseOrders(loadPurchaseOrders(productId, xUser));
        return detail;
    }

    @Override
    public List<ProductInventorySummaryDto> summarizeProducts(BulkInventorySummaryRequest request) {
        if (request.getProductIds() == null || request.getProductIds().isEmpty()) {
            return Collections.emptyList();
        }

        List<String> facilityIds = resolveFacilityIds(request.getProductStoreId());
        List<String> productIds = request.getProductIds().stream()
                .filter(StringUtils::hasText)
                .map(String::trim)
                .distinct()
                .collect(Collectors.toList());

        List<InventoryItem> items = facilityIds.isEmpty()
                ? inventoryItemRepository.findAll().stream()
                    .filter(item -> productIds.contains(item.getProductId()))
                    .collect(Collectors.toList())
                : inventoryItemRepository.findByFacilityIdInAndProductIdIn(facilityIds, productIds);

        Map<String, List<InventoryItem>> byProduct = items.stream()
                .collect(Collectors.groupingBy(InventoryItem::getProductId, LinkedHashMap::new, Collectors.toList()));

        List<ProductInventorySummaryDto> summaries = new ArrayList<>();
        for (String productId : productIds) {
            summaries.add(buildSummary(productId, request.getProductStoreId(),
                    byProduct.getOrDefault(productId, Collections.emptyList())));
        }
        return summaries;
    }

    private List<ProductPurchaseOrderLineDto> loadPurchaseOrders(String productId, String xUser) {
        OrderSearchClientRequest search = new OrderSearchClientRequest();
        search.setProductId(productId);
        search.setProductIdMatch("EQUALS");
        search.setOrderTypeId(PURCHASE_ORDER);
        search.setSize(100);

        List<ProductPurchaseOrderLineDto> lines = new ArrayList<>();
        for (OrderClientDto order : orderClient.findOrders(search, xUser)) {
            if (order.getItems() == null) {
                continue;
            }
            for (OrderItemClientDto item : order.getItems()) {
                if (!productId.equals(item.getProductId())) {
                    continue;
                }
                ProductPurchaseOrderLineDto line = new ProductPurchaseOrderLineDto();
                line.setOrderId(order.getOrderId());
                line.setOrderItemSeqId(item.getOrderItemSeqId());
                line.setSupplierPartyId(order.getPartyId());
                line.setStatusId(order.getStatusId());
                line.setOrderQuantity(item.getQuantity());
                BigDecimal received = shipmentReceiptRepository.sumAcceptedByOrderLine(
                        order.getOrderId(), productId, item.getOrderItemSeqId());
                line.setQuantityReceived(received);
                lines.add(line);
            }
        }
        return lines;
    }

    private List<FacilityInventoryBreakdownDto> buildFacilityBreakdown(List<InventoryItem> items) {
        Map<String, Facility> facilityCache = new HashMap<>();
        List<FacilityInventoryBreakdownDto> breakdown = new ArrayList<>();
        for (InventoryItem item : items) {
            FacilityInventoryBreakdownDto row = new FacilityInventoryBreakdownDto();
            row.setFacilityId(item.getFacilityId());
            row.setLocationSeqId(item.getLocationSeqId());
            Facility facility = facilityCache.computeIfAbsent(item.getFacilityId(),
                    id -> facilityRepository.findById(id).orElse(null));
            if (facility != null) {
                row.setFacilityName(facility.getFacilityName());
            }
            row.setQuantityOnHand(InventoryTotals.safe(item.getQuantityOnHandTotal()));
            row.setAvailableToPromise(InventoryTotals.safe(item.getAvailableToPromiseTotal()));
            row.setQuantityReserved(InventoryTotals.reservedForItem(item));
            breakdown.add(row);
        }
        return breakdown;
    }

    private ProductInventorySummaryDto buildSummary(String productId, String productStoreId, List<InventoryItem> items) {
        BigDecimal onHand = InventoryTotals.sumOnHand(items);
        BigDecimal reserved = InventoryTotals.sumReserved(items);
        BigDecimal atp = InventoryTotals.sumAtp(items);

        ProductInventorySummaryDto summary = new ProductInventorySummaryDto();
        summary.setProductId(productId);
        summary.setProductStoreId(productStoreId);
        summary.setQuantityOnHand(onHand);
        summary.setQuantityReserved(reserved);
        summary.setAvailableToPromise(atp);
        summary.setTotalInventory(onHand);
        return summary;
    }

    private List<InventoryItem> loadItems(List<String> facilityIds, String productId) {
        if (facilityIds.isEmpty()) {
            return inventoryItemRepository.findByProductId(productId);
        }
        return inventoryItemRepository.findByFacilityIdInAndProductId(facilityIds, productId);
    }

    private List<String> resolveFacilityIds(String productStoreId) {
        if (!StringUtils.hasText(productStoreId)) {
            return Collections.emptyList();
        }
        LocalDateTime now = LocalDateTime.now();
        return productStoreFacilityRepository.findByIdProductStoreId(productStoreId.trim()).stream()
                .filter(mapping -> mapping.getThruDate() == null || mapping.getThruDate().isAfter(now))
                .map(mapping -> mapping.getId().getFacilityId())
                .distinct()
                .collect(Collectors.toList());
    }
}
