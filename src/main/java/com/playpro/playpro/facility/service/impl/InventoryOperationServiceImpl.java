package com.playpro.playpro.facility.service.impl;

import com.playpro.playpro.facility.dto.InventoryOperationLineDto;
import com.playpro.playpro.facility.dto.InventoryOperationRequest;
import com.playpro.playpro.facility.entity.InventoryItem;
import com.playpro.playpro.facility.entity.InventoryItemDetail;
import com.playpro.playpro.facility.entity.InventoryItemDetailId;
import com.playpro.playpro.facility.entity.ProductStoreFacility;
import com.playpro.playpro.facility.repository.InventoryItemDetailRepository;
import com.playpro.playpro.facility.repository.InventoryItemRepository;
import com.playpro.playpro.facility.repository.ProductStoreFacilityRepository;
import com.playpro.playpro.facility.service.InventoryOperationService;
import com.playpro.playpro.facility.util.FacilityIdGenerator;
import com.playpro.playpro.facility.util.InventoryTotals;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class InventoryOperationServiceImpl implements InventoryOperationService {

    private final InventoryItemRepository inventoryItemRepository;
    private final InventoryItemDetailRepository inventoryItemDetailRepository;
    private final ProductStoreFacilityRepository productStoreFacilityRepository;

    public InventoryOperationServiceImpl(InventoryItemRepository inventoryItemRepository,
                                         InventoryItemDetailRepository inventoryItemDetailRepository,
                                         ProductStoreFacilityRepository productStoreFacilityRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
        this.inventoryItemDetailRepository = inventoryItemDetailRepository;
        this.productStoreFacilityRepository = productStoreFacilityRepository;
    }

    @Override
    public List<Map<String, Object>> reserveInventory(InventoryOperationRequest request, String principal) {
        validateRequest(request);
        List<Map<String, Object>> results = new ArrayList<>();
        int detailSeq = 1;
        LocalDateTime now = LocalDateTime.now();

        for (InventoryOperationLineDto line : request.getLines()) {
            BigDecimal remaining = line.getQuantity();
            List<InventoryItem> candidates = loadCandidates(request.getProductStoreId(), line.getProductId());
            candidates.sort(Comparator.comparing((InventoryItem item) ->
                    InventoryTotals.safe(item.getAvailableToPromiseTotal())).reversed());

            for (InventoryItem item : candidates) {
                if (remaining.signum() <= 0) {
                    break;
                }
                BigDecimal atp = InventoryTotals.safe(item.getAvailableToPromiseTotal());
                if (atp.signum() <= 0) {
                    continue;
                }
                BigDecimal reserveQty = atp.min(remaining);
                item.setAvailableToPromiseTotal(atp.subtract(reserveQty));
                inventoryItemRepository.save(item);

                InventoryItemDetail detail = new InventoryItemDetail();
                detail.setId(new InventoryItemDetailId(item.getInventoryItemId(),
                        FacilityIdGenerator.nextDetailSeqId(detailSeq++)));
                detail.setEffectiveDate(now);
                detail.setAvailableToPromiseDiff(reserveQty.negate());
                detail.setOrderId(request.getOrderId());
                detail.setOrderItemSeqId(line.getOrderItemSeqId());
                detail.setDescription("Sales order reservation");
                inventoryItemDetailRepository.save(detail);

                remaining = remaining.subtract(reserveQty);
                results.add(resultRow("reserve", item, line, reserveQty));
            }

            if (remaining.signum() > 0) {
                throw new IllegalArgumentException("Insufficient available inventory for product "
                        + line.getProductId() + ". Short by " + remaining);
            }
        }
        return results;
    }

    @Override
    public List<Map<String, Object>> issueInventory(InventoryOperationRequest request, String principal) {
        validateRequest(request);
        List<Map<String, Object>> results = new ArrayList<>();
        int detailSeq = 1;
        LocalDateTime now = LocalDateTime.now();

        for (InventoryOperationLineDto line : request.getLines()) {
            BigDecimal remaining = line.getQuantity();
            List<InventoryItem> candidates = loadCandidates(request.getProductStoreId(), line.getProductId());
            candidates.sort(Comparator.comparing(InventoryTotals::reservedForItem).reversed());

            for (InventoryItem item : candidates) {
                if (remaining.signum() <= 0) {
                    break;
                }
                BigDecimal reserved = InventoryTotals.reservedForItem(item);
                BigDecimal onHand = InventoryTotals.safe(item.getQuantityOnHandTotal());
                BigDecimal issueQty = onHand.min(remaining);
                if (reserved.signum() > 0) {
                    issueQty = reserved.min(remaining);
                }
                if (issueQty.signum() <= 0) {
                    continue;
                }

                item.setQuantityOnHandTotal(onHand.subtract(issueQty));
                item.setAccountingQuantityTotal(
                        InventoryTotals.safe(item.getAccountingQuantityTotal()).subtract(issueQty));
                if (reserved.signum() <= 0) {
                    item.setAvailableToPromiseTotal(
                            InventoryTotals.safe(item.getAvailableToPromiseTotal()).subtract(issueQty));
                }
                inventoryItemRepository.save(item);

                InventoryItemDetail detail = new InventoryItemDetail();
                detail.setId(new InventoryItemDetailId(item.getInventoryItemId(),
                        FacilityIdGenerator.nextDetailSeqId(detailSeq++)));
                detail.setEffectiveDate(now);
                detail.setQuantityOnHandDiff(issueQty.negate());
                detail.setAccountingQuantityDiff(issueQty.negate());
                if (reserved.signum() <= 0) {
                    detail.setAvailableToPromiseDiff(issueQty.negate());
                }
                detail.setOrderId(request.getOrderId());
                detail.setOrderItemSeqId(line.getOrderItemSeqId());
                detail.setDescription("Sales order issue");
                inventoryItemDetailRepository.save(detail);

                remaining = remaining.subtract(issueQty);
                results.add(resultRow("issue", item, line, issueQty));
            }

            if (remaining.signum() > 0) {
                throw new IllegalArgumentException("Unable to issue inventory for product "
                        + line.getProductId() + ". Remaining quantity " + remaining);
            }
        }
        return results;
    }

    @Override
    public List<Map<String, Object>> releaseInventory(InventoryOperationRequest request, String principal) {
        validateRequest(request);
        List<Map<String, Object>> results = new ArrayList<>();
        int detailSeq = 1;
        LocalDateTime now = LocalDateTime.now();

        for (InventoryOperationLineDto line : request.getLines()) {
            BigDecimal remaining = line.getQuantity();
            List<InventoryItem> candidates = loadCandidates(request.getProductStoreId(), line.getProductId());
            candidates.sort(Comparator.comparing(InventoryTotals::reservedForItem).reversed());

            for (InventoryItem item : candidates) {
                if (remaining.signum() <= 0) {
                    break;
                }
                BigDecimal reserved = InventoryTotals.reservedForItem(item);
                if (reserved.signum() <= 0) {
                    continue;
                }
                BigDecimal releaseQty = reserved.min(remaining);
                item.setAvailableToPromiseTotal(
                        InventoryTotals.safe(item.getAvailableToPromiseTotal()).add(releaseQty));
                inventoryItemRepository.save(item);

                InventoryItemDetail detail = new InventoryItemDetail();
                detail.setId(new InventoryItemDetailId(item.getInventoryItemId(),
                        FacilityIdGenerator.nextDetailSeqId(detailSeq++)));
                detail.setEffectiveDate(now);
                detail.setAvailableToPromiseDiff(releaseQty);
                detail.setOrderId(request.getOrderId());
                detail.setOrderItemSeqId(line.getOrderItemSeqId());
                detail.setDescription("Sales order reservation release");
                inventoryItemDetailRepository.save(detail);

                remaining = remaining.subtract(releaseQty);
                results.add(resultRow("release", item, line, releaseQty));
            }

            if (remaining.signum() > 0) {
                throw new IllegalArgumentException("Unable to release reservation for product "
                        + line.getProductId() + ". Remaining quantity " + remaining);
            }
        }
        return results;
    }

    private List<InventoryItem> loadCandidates(String productStoreId, String productId) {
        List<String> facilityIds = resolveFacilityIds(productStoreId);
        List<InventoryItem> items = facilityIds.isEmpty()
                ? inventoryItemRepository.findByProductId(productId)
                : inventoryItemRepository.findByFacilityIdInAndProductId(facilityIds, productId);
        if (items.isEmpty()) {
            throw new IllegalArgumentException("No inventory found for product " + productId);
        }
        return items;
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

    private void validateRequest(InventoryOperationRequest request) {
        if (!StringUtils.hasText(request.getOrderId())) {
            throw new IllegalArgumentException("orderId is required");
        }
        if (request.getLines() == null || request.getLines().isEmpty()) {
            throw new IllegalArgumentException("At least one line is required");
        }
        for (InventoryOperationLineDto line : request.getLines()) {
            if (!StringUtils.hasText(line.getProductId())) {
                throw new IllegalArgumentException("productId is required on each line");
            }
            if (line.getQuantity() == null || line.getQuantity().signum() <= 0) {
                throw new IllegalArgumentException("quantity must be positive on each line");
            }
        }
    }

    private Map<String, Object> resultRow(String action, InventoryItem item,
                                        InventoryOperationLineDto line, BigDecimal quantity) {
        Map<String, Object> row = new HashMap<>();
        row.put("action", action);
        row.put("inventoryItemId", item.getInventoryItemId());
        row.put("facilityId", item.getFacilityId());
        row.put("productId", line.getProductId());
        row.put("orderItemSeqId", line.getOrderItemSeqId());
        row.put("quantity", quantity);
        return row;
    }
}
