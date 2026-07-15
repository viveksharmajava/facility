package com.playpro.playpro.facility.dto;

import java.util.ArrayList;
import java.util.List;

public class ReceiveInventoryRequest {

    private String orderId;
    private List<ReceiveInventoryLineDto> lines = new ArrayList<>();

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<ReceiveInventoryLineDto> getLines() {
        return lines;
    }

    public void setLines(List<ReceiveInventoryLineDto> lines) {
        this.lines = lines;
    }
}
