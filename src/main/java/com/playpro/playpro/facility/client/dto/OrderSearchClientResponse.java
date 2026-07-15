package com.playpro.playpro.facility.client.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderSearchClientResponse {

    private List<OrderClientDto> content = new ArrayList<>();

    public List<OrderClientDto> getContent() {
        return content;
    }

    public void setContent(List<OrderClientDto> content) {
        this.content = content;
    }
}
