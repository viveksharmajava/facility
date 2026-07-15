package com.playpro.playpro.facility.service.impl;

import com.playpro.playpro.facility.client.OrderClient;
import com.playpro.playpro.facility.client.dto.OrderClientDto;
import com.playpro.playpro.facility.client.dto.OrderItemClientDto;
import com.playpro.playpro.facility.dto.PurchaseOrderLineDto;
import com.playpro.playpro.facility.dto.PurchaseOrderReceiptDto;
import com.playpro.playpro.facility.dto.ReceiveInventoryLineDto;
import com.playpro.playpro.facility.dto.ReceiveInventoryRequest;
import com.playpro.playpro.facility.entity.Facility;
import com.playpro.playpro.facility.entity.InventoryItem;
import com.playpro.playpro.facility.entity.InventoryItemDetail;
import com.playpro.playpro.facility.entity.InventoryItemDetailId;
import com.playpro.playpro.facility.entity.ShipmentReceipt;
import com.playpro.playpro.facility.exception.ResourceNotFoundException;
import com.playpro.playpro.facility.repository.FacilityRepository;
import com.playpro.playpro.facility.repository.InventoryItemDetailRepository;
import com.playpro.playpro.facility.repository.InventoryItemRepository;
import com.playpro.playpro.facility.repository.ShipmentReceiptRepository;
import com.playpro.playpro.facility.service.ReceiveInventoryService;
import com.playpro.playpro.facility.util.FacilityIdGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReceiveInventoryServiceImpl implements ReceiveInventoryService {

    private static final String PURCHASE_ORDER = "PURCHASE_ORDER";
    private static final String INV_AVAILABLE = "INV_AVAILABLE";

    private final FacilityRepository facilityRepository;
    private final InventoryItemRepository inventoryItemRepository;
    private final InventoryItemDetailRepository inventoryItemDetailRepository;
    private final ShipmentReceiptRepository shipmentReceiptRepository;
    private final OrderClient orderClient;

    public ReceiveInventoryServiceImpl(FacilityRepository facilityRepository,
                                         InventoryItemRepository inventoryItemRepository,
                                         InventoryItemDetailRepository inventoryItemDetailRepository,
                                         ShipmentReceiptRepository shipmentReceiptRepository,
                                         OrderClient orderClient) {
        this.facilityRepository = facilityRepository;
        this.inventoryItemRepository = inventoryItemRepository;
        this.inventoryItemDetailRepository = inventoryItemDetailRepository;
        this.shipmentReceiptRepository = shipmentReceiptRepository;
        this.orderClient = orderClient;
    }

    @Override
    @Transactional(readOnly = true)
    public PurchaseOrderReceiptDto getPurchaseOrderForReceipt(String facilityId, String orderId, String xUser) {
        loadFacility(facilityId);
        OrderClientDto order = orderClient.getOrder(orderId, xUser);
        if (order == null) {
            throw new ResourceNotFoundException("Order not found: " + orderId);
        }
        if (!PURCHASE_ORDER.equals(order.getOrderTypeId())) {
            throw new IllegalArgumentException("Order is not a purchase order: " + order.getOrderTypeId());
        }

        PurchaseOrderReceiptDto dto = new PurchaseOrderReceiptDto();
        dto.setOrderId(order.getOrderId());
        dto.setOrderTypeId(order.getOrderTypeId());
        dto.setStatusId(order.getStatusId());
        dto.setCurrencyUom(order.getCurrencyUom());
        dto.setProductStoreId(order.getProductStoreId());
        dto.setPartyId(order.getPartyId());
        if (order.getItems() != null) {
            dto.setLines(order.getItems().stream().map(this::toLineDto).collect(Collectors.toList()));
        }
        return dto;
    }

    @Override
    public List<Map<String, Object>> receiveInventory(String facilityId, ReceiveInventoryRequest request, String principal) {
        Facility facility = loadFacility(facilityId);
        if (!StringUtils.hasText(request.getOrderId())) {
            throw new IllegalArgumentException("orderId is required");
        }
        if (request.getLines() == null || request.getLines().isEmpty()) {
            throw new IllegalArgumentException("At least one receive line is required");
        }

        List<Map<String, Object>> results = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        int detailSeq = 1;

        for (ReceiveInventoryLineDto line : request.getLines()) {
            if (!StringUtils.hasText(line.getProductId())) {
                throw new IllegalArgumentException("productId is required on each line");
            }
            if (line.getQuantityAccepted() == null || line.getQuantityAccepted().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("quantityAccepted must be positive");
            }

            String locationSeqId = StringUtils.hasText(line.getLocationSeqId()) ? line.getLocationSeqId() : "DEFAULT";
            String inventoryItemTypeId = StringUtils.hasText(facility.getDefaultInventoryItemTypeId())
                    ? facility.getDefaultInventoryItemTypeId()
                    : "NON_SERIAL_INV_ITEM";

            InventoryItem item = inventoryItemRepository
                    .findByFacilityIdAndProductIdAndLocationSeqId(facilityId, line.getProductId(), locationSeqId)
                    .orElse(null);

            if (item == null) {
                item = new InventoryItem();
                item.setInventoryItemId(FacilityIdGenerator.nextInventoryItemId());
                item.setInventoryItemTypeId(inventoryItemTypeId);
                item.setProductId(line.getProductId());
                item.setFacilityId(facilityId);
                item.setLocationSeqId(locationSeqId);
                item.setStatusId(INV_AVAILABLE);
                item.setDatetimeReceived(now);
                item.setQuantityOnHandTotal(line.getQuantityAccepted());
                item.setAvailableToPromiseTotal(line.getQuantityAccepted());
                item.setAccountingQuantityTotal(line.getQuantityAccepted());
                item.setUnitCost(line.getUnitCost());
                item.setSerialNumber(line.getSerialNumber());
            } else {
                item.setQuantityOnHandTotal(safeAdd(item.getQuantityOnHandTotal(), line.getQuantityAccepted()));
                item.setAvailableToPromiseTotal(safeAdd(item.getAvailableToPromiseTotal(), line.getQuantityAccepted()));
                item.setAccountingQuantityTotal(safeAdd(item.getAccountingQuantityTotal(), line.getQuantityAccepted()));
                if (line.getUnitCost() != null) {
                    item.setUnitCost(line.getUnitCost());
                }
            }
            inventoryItemRepository.save(item);

            String receiptId = FacilityIdGenerator.nextReceiptId();
            ShipmentReceipt receipt = new ShipmentReceipt();
            receipt.setReceiptId(receiptId);
            receipt.setInventoryItemId(item.getInventoryItemId());
            receipt.setProductId(line.getProductId());
            receipt.setOrderId(request.getOrderId());
            receipt.setOrderItemSeqId(line.getOrderItemSeqId());
            receipt.setReceivedByUserLoginId(principal);
            receipt.setDatetimeReceived(now);
            receipt.setQuantityAccepted(line.getQuantityAccepted());
            receipt.setQuantityRejected(BigDecimal.ZERO);
            shipmentReceiptRepository.save(receipt);

            InventoryItemDetail detail = new InventoryItemDetail();
            detail.setId(new InventoryItemDetailId(item.getInventoryItemId(), FacilityIdGenerator.nextDetailSeqId(detailSeq++)));
            detail.setEffectiveDate(now);
            detail.setQuantityOnHandDiff(line.getQuantityAccepted());
            detail.setAvailableToPromiseDiff(line.getQuantityAccepted());
            detail.setAccountingQuantityDiff(line.getQuantityAccepted());
            detail.setUnitCost(line.getUnitCost());
            detail.setOrderId(request.getOrderId());
            detail.setOrderItemSeqId(line.getOrderItemSeqId());
            detail.setReceiptId(receiptId);
            detail.setDescription("Purchase order receipt");
            inventoryItemDetailRepository.save(detail);

            Map<String, Object> result = new HashMap<>();
            result.put("inventoryItemId", item.getInventoryItemId());
            result.put("receiptId", receiptId);
            result.put("productId", line.getProductId());
            result.put("quantityAccepted", line.getQuantityAccepted());
            results.add(result);
        }
        return results;
    }

    private PurchaseOrderLineDto toLineDto(OrderItemClientDto item) {
        PurchaseOrderLineDto line = new PurchaseOrderLineDto();
        line.setOrderItemSeqId(item.getOrderItemSeqId());
        line.setProductId(item.getProductId());
        line.setOrderQuantity(item.getQuantity());
        line.setUnitPrice(item.getUnitPrice());
        line.setStatusId(item.getStatusId());
        return line;
    }

    private BigDecimal safeAdd(BigDecimal current, BigDecimal delta) {
        if (current == null) {
            return delta;
        }
        return current.add(delta);
    }

    private Facility loadFacility(String facilityId) {
        return facilityRepository.findById(facilityId)
                .orElseThrow(() -> new ResourceNotFoundException("Facility not found: " + facilityId));
    }
}
