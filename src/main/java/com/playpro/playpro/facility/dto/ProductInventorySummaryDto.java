package com.playpro.playpro.facility.dto;

import java.math.BigDecimal;

public class ProductInventorySummaryDto {

    private String productId;
    private String productStoreId;
    private BigDecimal quantityOnHand;
    private BigDecimal quantityReserved;
    private BigDecimal availableToPromise;
    private BigDecimal totalInventory;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductStoreId() {
        return productStoreId;
    }

    public void setProductStoreId(String productStoreId) {
        this.productStoreId = productStoreId;
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

    public BigDecimal getTotalInventory() {
        return totalInventory;
    }

    public void setTotalInventory(BigDecimal totalInventory) {
        this.totalInventory = totalInventory;
    }
}
