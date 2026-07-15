package com.playpro.playpro.facility.dto;

import java.util.ArrayList;
import java.util.List;

public class BulkInventorySummaryRequest {

    private String productStoreId;
    private List<String> productIds = new ArrayList<>();

    public String getProductStoreId() {
        return productStoreId;
    }

    public void setProductStoreId(String productStoreId) {
        this.productStoreId = productStoreId;
    }

    public List<String> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<String> productIds) {
        this.productIds = productIds;
    }
}
