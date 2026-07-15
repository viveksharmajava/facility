package com.playpro.playpro.facility.client.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderSearchClientRequest {

    private String productId;
    private String productIdMatch = "EQUALS";
    private String orderTypeId;
    private int page = 0;
    private int size = 100;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductIdMatch() {
        return productIdMatch;
    }

    public void setProductIdMatch(String productIdMatch) {
        this.productIdMatch = productIdMatch;
    }

    public String getOrderTypeId() {
        return orderTypeId;
    }

    public void setOrderTypeId(String orderTypeId) {
        this.orderTypeId = orderTypeId;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
