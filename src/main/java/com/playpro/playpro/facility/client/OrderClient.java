package com.playpro.playpro.facility.client;

import com.playpro.playpro.facility.client.dto.OrderClientDto;
import com.playpro.playpro.facility.client.dto.OrderSearchClientRequest;
import com.playpro.playpro.facility.client.dto.OrderSearchClientResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
public class OrderClient {

    private final RestTemplate restTemplate;
    private final String ordersBaseUrl;

    public OrderClient(RestTemplate restTemplate,
                       @Value("${orders.service.base-url:http://localhost:8083}") String ordersBaseUrl) {
        this.restTemplate = restTemplate;
        this.ordersBaseUrl = ordersBaseUrl;
    }

    public OrderClientDto getOrder(String orderId, String xUser) {
        try {
            ResponseEntity<OrderClientDto> response = restTemplate.exchange(
                    ordersBaseUrl + "/orders/" + orderId,
                    HttpMethod.GET,
                    new HttpEntity<>(headers(xUser)),
                    OrderClientDto.class);
            return response.getBody();
        } catch (RestClientException ex) {
            throw new IllegalArgumentException("Unable to fetch order " + orderId + ": " + ex.getMessage());
        }
    }

    public List<OrderClientDto> findOrders(OrderSearchClientRequest request, String xUser) {
        try {
            ResponseEntity<OrderSearchClientResponse> response = restTemplate.exchange(
                    ordersBaseUrl + "/orders/find",
                    HttpMethod.POST,
                    new HttpEntity<>(request, headers(xUser)),
                    OrderSearchClientResponse.class);
            if (response.getBody() == null || response.getBody().getContent() == null) {
                return Collections.emptyList();
            }
            return response.getBody().getContent();
        } catch (RestClientException ex) {
            throw new IllegalArgumentException("Unable to search orders: " + ex.getMessage());
        }
    }

    private HttpHeaders headers(String xUser) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (xUser != null && !xUser.trim().isEmpty()) {
            headers.set("X-User", xUser);
        }
        return headers;
    }
}
