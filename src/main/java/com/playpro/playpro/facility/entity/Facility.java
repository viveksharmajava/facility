package com.playpro.playpro.facility.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "facility")
public class Facility {

    @Id
    @Column(name = "facility_id", length = 20)
    private String facilityId;

    @Column(name = "facility_type_id", length = 20, nullable = false)
    private String facilityTypeId;

    @Column(name = "parent_facility_id", length = 20)
    private String parentFacilityId;

    @Column(name = "owner_party_id", length = 20)
    private String ownerPartyId;

    @Column(name = "default_inventory_item_type_id", length = 20)
    private String defaultInventoryItemTypeId;

    @Column(name = "facility_name", length = 100)
    private String facilityName;

    @Column(name = "primary_facility_group_id", length = 20)
    private String primaryFacilityGroupId;

    @Column(name = "facility_size", precision = 18, scale = 3)
    private BigDecimal facilitySize;

    @Column(name = "facility_size_uom_id", length = 20)
    private String facilitySizeUomId;

    @Column(name = "product_store_id", length = 20)
    private String productStoreId;

    @Column(name = "default_days_to_ship")
    private Long defaultDaysToShip;

    @Column(name = "opened_date")
    private LocalDateTime openedDate;

    @Column(name = "closed_date")
    private LocalDateTime closedDate;

    @Column(length = 255)
    private String description;

    @Column(name = "default_dimension_uom_id", length = 20)
    private String defaultDimensionUomId;

    @Column(name = "default_weight_uom_id", length = 20)
    private String defaultWeightUomId;

    @Column(name = "geo_point_id", length = 20)
    private String geoPointId;

    @Column(name = "facility_level")
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
