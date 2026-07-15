package com.playpro.playpro.facility.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_item")
public class InventoryItem {

    @Id
    @Column(name = "inventory_item_id", length = 20)
    private String inventoryItemId;

    @Column(name = "inventory_item_type_id", length = 20, nullable = false)
    private String inventoryItemTypeId;

    @Column(name = "product_id", length = 20)
    private String productId;

    @Column(name = "party_id", length = 20)
    private String partyId;

    @Column(name = "owner_party_id", length = 20)
    private String ownerPartyId;

    @Column(name = "status_id", length = 20)
    private String statusId;

    @Column(name = "datetime_received")
    private LocalDateTime datetimeReceived;

    @Column(name = "datetime_manufactured")
    private LocalDateTime datetimeManufactured;

    @Column(name = "expire_date")
    private LocalDateTime expireDate;

    @Column(name = "facility_id", length = 20)
    private String facilityId;

    @Column(name = "container_id", length = 20)
    private String containerId;

    @Column(name = "lot_id", length = 20)
    private String lotId;

    @Column(name = "uom_id", length = 20)
    private String uomId;

    @Column(name = "bin_number", length = 20)
    private String binNumber;

    @Column(name = "location_seq_id", length = 20)
    private String locationSeqId;

    @Column(length = 255)
    private String comments;

    @Column(name = "quantity_on_hand_total", precision = 18, scale = 3)
    private BigDecimal quantityOnHandTotal;

    @Column(name = "available_to_promise_total", precision = 18, scale = 3)
    private BigDecimal availableToPromiseTotal;

    @Column(name = "accounting_quantity_total", precision = 18, scale = 3)
    private BigDecimal accountingQuantityTotal;

    @Column(name = "serial_number", length = 255)
    private String serialNumber;

    @Column(name = "soft_identifier", length = 255)
    private String softIdentifier;

    @Column(name = "activation_number", length = 255)
    private String activationNumber;

    @Column(name = "activation_valid_thru")
    private LocalDateTime activationValidThru;

    @Column(name = "unit_cost", precision = 18, scale = 3)
    private BigDecimal unitCost;

    @Column(name = "currency_uom_id", length = 20)
    private String currencyUomId;

    @Column(name = "fixed_asset_id", length = 20)
    private String fixedAssetId;

    public String getInventoryItemId() {
        return inventoryItemId;
    }

    public void setInventoryItemId(String inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
    }

    public String getInventoryItemTypeId() {
        return inventoryItemTypeId;
    }

    public void setInventoryItemTypeId(String inventoryItemTypeId) {
        this.inventoryItemTypeId = inventoryItemTypeId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getOwnerPartyId() {
        return ownerPartyId;
    }

    public void setOwnerPartyId(String ownerPartyId) {
        this.ownerPartyId = ownerPartyId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public LocalDateTime getDatetimeReceived() {
        return datetimeReceived;
    }

    public void setDatetimeReceived(LocalDateTime datetimeReceived) {
        this.datetimeReceived = datetimeReceived;
    }

    public LocalDateTime getDatetimeManufactured() {
        return datetimeManufactured;
    }

    public void setDatetimeManufactured(LocalDateTime datetimeManufactured) {
        this.datetimeManufactured = datetimeManufactured;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public String getLotId() {
        return lotId;
    }

    public void setLotId(String lotId) {
        this.lotId = lotId;
    }

    public String getUomId() {
        return uomId;
    }

    public void setUomId(String uomId) {
        this.uomId = uomId;
    }

    public String getBinNumber() {
        return binNumber;
    }

    public void setBinNumber(String binNumber) {
        this.binNumber = binNumber;
    }

    public String getLocationSeqId() {
        return locationSeqId;
    }

    public void setLocationSeqId(String locationSeqId) {
        this.locationSeqId = locationSeqId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public BigDecimal getQuantityOnHandTotal() {
        return quantityOnHandTotal;
    }

    public void setQuantityOnHandTotal(BigDecimal quantityOnHandTotal) {
        this.quantityOnHandTotal = quantityOnHandTotal;
    }

    public BigDecimal getAvailableToPromiseTotal() {
        return availableToPromiseTotal;
    }

    public void setAvailableToPromiseTotal(BigDecimal availableToPromiseTotal) {
        this.availableToPromiseTotal = availableToPromiseTotal;
    }

    public BigDecimal getAccountingQuantityTotal() {
        return accountingQuantityTotal;
    }

    public void setAccountingQuantityTotal(BigDecimal accountingQuantityTotal) {
        this.accountingQuantityTotal = accountingQuantityTotal;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSoftIdentifier() {
        return softIdentifier;
    }

    public void setSoftIdentifier(String softIdentifier) {
        this.softIdentifier = softIdentifier;
    }

    public String getActivationNumber() {
        return activationNumber;
    }

    public void setActivationNumber(String activationNumber) {
        this.activationNumber = activationNumber;
    }

    public LocalDateTime getActivationValidThru() {
        return activationValidThru;
    }

    public void setActivationValidThru(LocalDateTime activationValidThru) {
        this.activationValidThru = activationValidThru;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public String getCurrencyUomId() {
        return currencyUomId;
    }

    public void setCurrencyUomId(String currencyUomId) {
        this.currencyUomId = currencyUomId;
    }

    public String getFixedAssetId() {
        return fixedAssetId;
    }

    public void setFixedAssetId(String fixedAssetId) {
        this.fixedAssetId = fixedAssetId;
    }
}
