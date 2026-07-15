package com.playpro.playpro.facility.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ReceiveInventoryLineDto {

    private String orderItemSeqId;
    private String productId;
    private BigDecimal quantityAccepted;
    private BigDecimal unitCost;
    private String locationSeqId;
    private String serialNumber;

    public String getOrderItemSeqId() {
        return orderItemSeqId;
    }

    public void setOrderItemSeqId(String orderItemSeqId) {
        this.orderItemSeqId = orderItemSeqId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public BigDecimal getQuantityAccepted() {
        return quantityAccepted;
    }

    public void setQuantityAccepted(BigDecimal quantityAccepted) {
        this.quantityAccepted = quantityAccepted;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public String getLocationSeqId() {
        return locationSeqId;
    }

    public void setLocationSeqId(String locationSeqId) {
        this.locationSeqId = locationSeqId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
