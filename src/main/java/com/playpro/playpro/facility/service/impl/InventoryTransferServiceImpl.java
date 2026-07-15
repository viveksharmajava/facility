package com.playpro.playpro.facility.service.impl;

import com.playpro.playpro.facility.dto.CreateInventoryTransferRequest;
import com.playpro.playpro.facility.dto.InventoryTransferDto;
import com.playpro.playpro.facility.entity.Facility;
import com.playpro.playpro.facility.entity.InventoryItem;
import com.playpro.playpro.facility.entity.InventoryTransfer;
import com.playpro.playpro.facility.exception.ResourceNotFoundException;
import com.playpro.playpro.facility.mapper.FacilityMapper;
import com.playpro.playpro.facility.repository.FacilityRepository;
import com.playpro.playpro.facility.repository.InventoryItemRepository;
import com.playpro.playpro.facility.repository.InventoryTransferRepository;
import com.playpro.playpro.facility.service.InventoryTransferService;
import com.playpro.playpro.facility.util.FacilityIdGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InventoryTransferServiceImpl implements InventoryTransferService {

    private static final String IXF_COMPLETE = "IXF_COMPLETE";

    private final InventoryTransferRepository inventoryTransferRepository;
    private final InventoryItemRepository inventoryItemRepository;
    private final FacilityRepository facilityRepository;

    public InventoryTransferServiceImpl(InventoryTransferRepository inventoryTransferRepository,
                                          InventoryItemRepository inventoryItemRepository,
                                          FacilityRepository facilityRepository) {
        this.inventoryTransferRepository = inventoryTransferRepository;
        this.inventoryItemRepository = inventoryItemRepository;
        this.facilityRepository = facilityRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventoryTransferDto> listByFacility(String facilityId) {
        loadFacility(facilityId);
        return inventoryTransferRepository.findByFacilityIdOrFacilityIdToOrderBySendDateDesc(facilityId, facilityId)
                .stream()
                .map(transfer -> {
                    InventoryItem item = inventoryItemRepository.findById(transfer.getInventoryItemId()).orElse(null);
                    InventoryTransferDto dto = FacilityMapper.toTransferDto(transfer, item);
                    if (item != null) {
                        dto.setQuantity(item.getQuantityOnHandTotal());
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public InventoryTransferDto createTransfer(String facilityId, CreateInventoryTransferRequest request, String principal) {
        loadFacility(facilityId);
        if (!StringUtils.hasText(request.getInventoryItemId())) {
            throw new IllegalArgumentException("inventoryItemId is required");
        }
        if (!StringUtils.hasText(request.getFacilityIdTo())) {
            throw new IllegalArgumentException("facilityIdTo is required");
        }
        loadFacility(request.getFacilityIdTo());

        InventoryItem item = inventoryItemRepository.findById(request.getInventoryItemId())
                .orElseThrow(() -> new ResourceNotFoundException("Inventory item not found: " + request.getInventoryItemId()));

        if (!facilityId.equals(item.getFacilityId())) {
            throw new IllegalArgumentException("Inventory item is not in facility: " + facilityId);
        }

        BigDecimal quantity = request.getQuantity() != null ? request.getQuantity() : item.getQuantityOnHandTotal();
        if (quantity == null || quantity.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("quantity must be positive");
        }
        if (item.getQuantityOnHandTotal() != null && quantity.compareTo(item.getQuantityOnHandTotal()) > 0) {
            throw new IllegalArgumentException("Insufficient quantity on hand");
        }

        LocalDateTime now = LocalDateTime.now();
        String locationSeqIdTo = StringUtils.hasText(request.getLocationSeqIdTo()) ? request.getLocationSeqIdTo() : "DEFAULT";

        InventoryTransfer transfer = new InventoryTransfer();
        transfer.setInventoryTransferId(FacilityIdGenerator.nextInventoryTransferId());
        transfer.setStatusId(IXF_COMPLETE);
        transfer.setInventoryItemId(item.getInventoryItemId());
        transfer.setFacilityId(facilityId);
        transfer.setLocationSeqId(item.getLocationSeqId());
        transfer.setFacilityIdTo(request.getFacilityIdTo());
        transfer.setLocationSeqIdTo(locationSeqIdTo);
        transfer.setSendDate(now);
        transfer.setReceiveDate(now);
        transfer.setComments(request.getComments());
        inventoryTransferRepository.save(transfer);

        item.setFacilityId(request.getFacilityIdTo());
        item.setLocationSeqId(locationSeqIdTo);
        inventoryItemRepository.save(item);

        InventoryTransferDto dto = FacilityMapper.toTransferDto(transfer, item);
        dto.setQuantity(quantity);
        return dto;
    }

    private Facility loadFacility(String facilityId) {
        return facilityRepository.findById(facilityId)
                .orElseThrow(() -> new ResourceNotFoundException("Facility not found: " + facilityId));
    }
}
