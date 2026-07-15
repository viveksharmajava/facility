package com.playpro.playpro.facility.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FacilityDto {

    private String facilityId;
    private String facilityTypeId;
    private String parentFacilityId;
    private String ownerPartyId;
    private String defaultInventoryItemTypeId;
    private String facilityName;
    private String primaryFacilityGroupId;
    private BigDecimal facilitySize;
    private String facilitySizeUomId;
    private String productStoreId;
    private Long defaultDaysToShip;
    private LocalDateTime openedDate;
    private LocalDateTime closedDate;
    private String description;
    private String defaultDimensionUomId;
    private String defaultWeightUomId;
    private String geoPointId;
    private Long facilityLevel;

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getFacilityTypeId() {
        return facilityTypeId;
    }

    public void setFacilityTypeId(String facilityTypeId) {
        this.facilityTypeId = facilityTypeId;
    }

    public String getParentFacilityId() {
        return parentFacilityId;
    }

    public void setParentFacilityId(String parentFacilityId) {
        this.parentFacilityId = parentFacilityId;
    }

    public String getOwnerPartyId() {
        return ownerPartyId;
    }

    public void setOwnerPartyId(String ownerPartyId) {
        this.ownerPartyId = ownerPartyId;
    }

    public String getDefaultInventoryItemTypeId() {
        return defaultInventoryItemTypeId;
    }

    public void setDefaultInventoryItemTypeId(String defaultInventoryItemTypeId) {
        this.defaultInventoryItemTypeId = defaultInventoryItemTypeId;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getPrimaryFacilityGroupId() {
        return primaryFacilityGroupId;
    }

    public void setPrimaryFacilityGroupId(String primaryFacilityGroupId) {
        this.primaryFacilityGroupId = primaryFacilityGroupId;
    }

    public BigDecimal getFacilitySize() {
        return facilitySize;
    }

    public void setFacilitySize(BigDecimal facilitySize) {
        this.facilitySize = facilitySize;
    }

    public String getFacilitySizeUomId() {
        return facilitySizeUomId;
    }

    public void setFacilitySizeUomId(String facilitySizeUomId) {
        this.facilitySizeUomId = facilitySizeUomId;
    }

    public String getProductStoreId() {
        return productStoreId;
    }

    public void setProductStoreId(String productStoreId) {
        this.productStoreId = productStoreId;
    }

    public Long getDefaultDaysToShip() {
        return defaultDaysToShip;
    }

    public void setDefaultDaysToShip(Long defaultDaysToShip) {
        this.defaultDaysToShip = defaultDaysToShip;
    }

    public LocalDateTime getOpenedDate() {
        return openedDate;
    }

    public void setOpenedDate(LocalDateTime openedDate) {
        this.openedDate = openedDate;
    }

    public LocalDateTime getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(LocalDateTime closedDate) {
        this.closedDate = closedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefaultDimensionUomId() {
        return defaultDimensionUomId;
    }

    public void setDefaultDimensionUomId(String defaultDimensionUomId) {
        this.defaultDimensionUomId = defaultDimensionUomId;
    }

    public String getDefaultWeightUomId() {
        return defaultWeightUomId;
    }

    public void setDefaultWeightUomId(String defaultWeightUomId) {
        this.defaultWeightUomId = defaultWeightUomId;
    }

    public String getGeoPointId() {
        return geoPointId;
    }

    public void setGeoPointId(String geoPointId) {
        this.geoPointId = geoPointId;
    }

    public Long getFacilityLevel() {
        return facilityLevel;
    }

    public void setFacilityLevel(Long facilityLevel) {
        this.facilityLevel = facilityLevel;
    }
}
