package com.playpro.playpro.facility.dto;

import java.math.BigDecimal;

public class FacilityInventoryBreakdownDto {

    private String facilityId;
    private String facilityName;
    private String locationSeqId;
    private BigDecimal quantityOnHand;
    private BigDecimal quantityReserved;
    private BigDecimal availableToPromise;

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getLocationSeqId() {
        return locationSeqId;
    }

    public void setLocationSeqId(String locationSeqId) {
        this.locationSeqId = locationSeqId;
    }

    public BigDecimal getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(BigDecimal quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }

    public BigDecimal getQuantityReserved() {
        return quantityReserved;
    }

    public void setQuantityReserved(BigDecimal quantityReserved) {
        this.quantityReserved = quantityReserved;
    }

    public BigDecimal getAvailableToPromise() {
        return availableToPromise;
    }

    public void setAvailableToPromise(BigDecimal availableToPromise) {
        this.availableToPromise = availableToPromise;
    }
}
