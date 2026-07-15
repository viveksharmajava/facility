package com.playpro.playpro.facility.mapper;

import com.playpro.playpro.facility.dto.FacilityDto;
import com.playpro.playpro.facility.dto.FacilitySummaryDto;
import com.playpro.playpro.facility.dto.InventoryTransferDto;
import com.playpro.playpro.facility.dto.ProductStoreFacilityDto;
import com.playpro.playpro.facility.entity.Facility;
import com.playpro.playpro.facility.entity.InventoryItem;
import com.playpro.playpro.facility.entity.InventoryTransfer;
import com.playpro.playpro.facility.entity.ProductStoreFacility;

public final class FacilityMapper {

    private FacilityMapper() {
    }

    public static FacilitySummaryDto toSummaryDto(Facility entity) {
        FacilitySummaryDto dto = new FacilitySummaryDto();
        dto.setFacilityId(entity.getFacilityId());
        dto.setFacilityName(entity.getFacilityName());
        dto.setFacilityTypeId(entity.getFacilityTypeId());
        dto.setDescription(entity.getDescription());
        dto.setProductStoreId(entity.getProductStoreId());
        return dto;
    }

    public static FacilityDto toDto(Facility entity) {
        FacilityDto dto = new FacilityDto();
        dto.setFacilityId(entity.getFacilityId());
        dto.setFacilityTypeId(entity.getFacilityTypeId());
        dto.setParentFacilityId(entity.getParentFacilityId());
        dto.setOwnerPartyId(entity.getOwnerPartyId());
        dto.setDefaultInventoryItemTypeId(entity.getDefaultInventoryItemTypeId());
        dto.setFacilityName(entity.getFacilityName());
        dto.setPrimaryFacilityGroupId(entity.getPrimaryFacilityGroupId());
        dto.setFacilitySize(entity.getFacilitySize());
        dto.setFacilitySizeUomId(entity.getFacilitySizeUomId());
        dto.setProductStoreId(entity.getProductStoreId());
        dto.setDefaultDaysToShip(entity.getDefaultDaysToShip());
        dto.setOpenedDate(entity.getOpenedDate());
        dto.setClosedDate(entity.getClosedDate());
        dto.setDescription(entity.getDescription());
        dto.setDefaultDimensionUomId(entity.getDefaultDimensionUomId());
        dto.setDefaultWeightUomId(entity.getDefaultWeightUomId());
        dto.setGeoPointId(entity.getGeoPointId());
        dto.setFacilityLevel(entity.getFacilityLevel());
        return dto;
    }

    public static void applyDtoToEntity(FacilityDto dto, Facility entity) {
        if (dto.getFacilityTypeId() != null) {
            entity.setFacilityTypeId(dto.getFacilityTypeId());
        }
        entity.setParentFacilityId(dto.getParentFacilityId());
        entity.setOwnerPartyId(dto.getOwnerPartyId());
        entity.setDefaultInventoryItemTypeId(dto.getDefaultInventoryItemTypeId());
        entity.setFacilityName(dto.getFacilityName());
        entity.setPrimaryFacilityGroupId(dto.getPrimaryFacilityGroupId());
        entity.setFacilitySize(dto.getFacilitySize());
        entity.setFacilitySizeUomId(dto.getFacilitySizeUomId());
        entity.setProductStoreId(dto.getProductStoreId());
        entity.setDefaultDaysToShip(dto.getDefaultDaysToShip());
        entity.setOpenedDate(dto.getOpenedDate());
        entity.setClosedDate(dto.getClosedDate());
        entity.setDescription(dto.getDescription());
        entity.setDefaultDimensionUomId(dto.getDefaultDimensionUomId());
        entity.setDefaultWeightUomId(dto.getDefaultWeightUomId());
        entity.setGeoPointId(dto.getGeoPointId());
        entity.setFacilityLevel(dto.getFacilityLevel());
    }

    public static ProductStoreFacilityDto toProductStoreFacilityDto(ProductStoreFacility mapping, Facility facility) {
        ProductStoreFacilityDto dto = new ProductStoreFacilityDto();
        dto.setProductStoreId(mapping.getId().getProductStoreId());
        dto.setFacilityId(mapping.getId().getFacilityId());
        dto.setFromDate(mapping.getId().getFromDate());
        dto.setThruDate(mapping.getThruDate());
        dto.setSequenceNum(mapping.getSequenceNum());
        if (facility != null) {
            dto.setFacilityName(facility.getFacilityName());
            dto.setFacilityTypeId(facility.getFacilityTypeId());
        }
        return dto;
    }

    public static InventoryTransferDto toTransferDto(InventoryTransfer transfer, InventoryItem item) {
        InventoryTransferDto dto = new InventoryTransferDto();
        dto.setInventoryTransferId(transfer.getInventoryTransferId());
        dto.setStatusId(transfer.getStatusId());
        dto.setInventoryItemId(transfer.getInventoryItemId());
        if (item != null) {
            dto.setProductId(item.getProductId());
        }
        dto.setFacilityId(transfer.getFacilityId());
        dto.setLocationSeqId(transfer.getLocationSeqId());
        dto.setFacilityIdTo(transfer.getFacilityIdTo());
        dto.setLocationSeqIdTo(transfer.getLocationSeqIdTo());
        dto.setSendDate(transfer.getSendDate());
        dto.setReceiveDate(transfer.getReceiveDate());
        dto.setComments(transfer.getComments());
        return dto;
    }
}
