package com.playpro.playpro.facility.dto;

import java.util.ArrayList;
import java.util.List;

public class InventoryOperationRequest {

    private String orderId;
    private String productStoreId;
    private List<InventoryOperationLineDto> lines = new ArrayList<>();

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductStoreId() {
        return productStoreId;
    }

    public void setProductStoreId(String productStoreId) {
        this.productStoreId = productStoreId;
    }

    public List<InventoryOperationLineDto> getLines() {
        return lines;
    }

    public void setLines(List<InventoryOperationLineDto> lines) {
        this.lines = lines;
    }
}
