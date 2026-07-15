package com.playpro.playpro.facility.dto;

import java.math.BigDecimal;

public class CreateInventoryTransferRequest {

    private String inventoryItemId;
    private String facilityIdTo;
    private String locationSeqIdTo;
    private BigDecimal quantity;
    private String comments;

    public String getInventoryItemId() {
        return inventoryItemId;
    }

    public void setInventoryItemId(String inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
    }

    public String getFacilityIdTo() {
        return facilityIdTo;
    }

    public void setFacilityIdTo(String facilityIdTo) {
        this.facilityIdTo = facilityIdTo;
    }

    public String getLocationSeqIdTo() {
        return locationSeqIdTo;
    }

    public void setLocationSeqIdTo(String locationSeqIdTo) {
        this.locationSeqIdTo = locationSeqIdTo;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
