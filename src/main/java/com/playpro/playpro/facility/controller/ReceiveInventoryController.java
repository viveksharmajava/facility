package com.playpro.playpro.facility.controller;

import com.playpro.playpro.facility.dto.PurchaseOrderReceiptDto;
import com.playpro.playpro.facility.dto.ReceiveInventoryRequest;
import com.playpro.playpro.facility.service.ReceiveInventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/facility/facilities/{facilityId}/receive-inventory")
public class ReceiveInventoryController {

    private final ReceiveInventoryService receiveInventoryService;

    public ReceiveInventoryController(ReceiveInventoryService receiveInventoryService) {
        this.receiveInventoryService = receiveInventoryService;
    }

    @GetMapping("/purchase-orders/{orderId}")
    public ResponseEntity<PurchaseOrderReceiptDto> getPurchaseOrder(@RequestHeader(value = "X-User", required = false) String xUser,
                                                                    @PathVariable String facilityId,
                                                                    @PathVariable String orderId) {
        return ResponseEntity.ok(receiveInventoryService.getPurchaseOrderForReceipt(facilityId, orderId, xUser));
    }

    @PostMapping
    public ResponseEntity<List<Map<String, Object>>> receiveInventory(@RequestHeader(value = "X-User", required = false) String xUser,
                                                                      @PathVariable String facilityId,
                                                                      @RequestBody ReceiveInventoryRequest request) {
        return ResponseEntity.ok(receiveInventoryService.receiveInventory(facilityId, request, resolvePrincipal(xUser)));
    }

    private String resolvePrincipal(String xUser) {
        if (xUser == null || !xUser.contains(":")) {
            return "system";
        }
        return xUser.split(":", 2)[0];
    }
}
