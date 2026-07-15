package com.playpro.playpro.facility.dto;

import java.util.ArrayList;
import java.util.List;

public class ProductInventoryDetailDto {

    private ProductInventorySummaryDto summary;
    private List<FacilityInventoryBreakdownDto> facilities = new ArrayList<>();
    private List<ProductPurchaseOrderLineDto> purchaseOrders = new ArrayList<>();

    public ProductInventorySummaryDto getSummary() {
        return summary;
    }

    public void setSummary(ProductInventorySummaryDto summary) {
        this.summary = summary;
    }

    public List<FacilityInventoryBreakdownDto> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<FacilityInventoryBreakdownDto> facilities) {
        this.facilities = facilities;
    }

    public List<ProductPurchaseOrderLineDto> getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(List<ProductPurchaseOrderLineDto> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }
}
