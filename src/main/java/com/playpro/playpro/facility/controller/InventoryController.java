package com.playpro.playpro.facility.controller;

import com.playpro.playpro.facility.dto.BulkInventorySummaryRequest;
import com.playpro.playpro.facility.dto.InventoryOperationRequest;
import com.playpro.playpro.facility.dto.ProductInventoryDetailDto;
import com.playpro.playpro.facility.dto.ProductInventorySummaryDto;
import com.playpro.playpro.facility.service.InventoryOperationService;
import com.playpro.playpro.facility.service.InventoryQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/facility/inventory")
public class InventoryController {

    private final InventoryQueryService inventoryQueryService;
    private final InventoryOperationService inventoryOperationService;

    public InventoryController(InventoryQueryService inventoryQueryService,
                               InventoryOperationService inventoryOperationService) {
        this.inventoryQueryService = inventoryQueryService;
        this.inventoryOperationService = inventoryOperationService;
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductInventoryDetailDto> getProductInventory(
            @PathVariable String productId,
            @RequestParam(value = "productStoreId", required = false) String productStoreId,
            @RequestHeader(value = "X-User", required = false) String xUser) {
        return ResponseEntity.ok(inventoryQueryService.getProductInventory(productId, productStoreId, xUser));
    }

    @PostMapping("/summaries")
    public ResponseEntity<List<ProductInventorySummaryDto>> summarizeProducts(
            @RequestBody BulkInventorySummaryRequest request) {
        return ResponseEntity.ok(inventoryQueryService.summarizeProducts(request));
    }

    @PostMapping("/reserve")
    public ResponseEntity<List<Map<String, Object>>> reserveInventory(
            @RequestHeader(value = "X-User", required = false) String xUser,
            @RequestBody InventoryOperationRequest request) {
        return ResponseEntity.ok(inventoryOperationService.reserveInventory(request, resolvePrincipal(xUser)));
    }

    @PostMapping("/issue")
    public ResponseEntity<List<Map<String, Object>>> issueInventory(
            @RequestHeader(value = "X-User", required = false) String xUser,
            @RequestBody InventoryOperationRequest request) {
        return ResponseEntity.ok(inventoryOperationService.issueInventory(request, resolvePrincipal(xUser)));
    }

    @PostMapping("/release")
    public ResponseEntity<List<Map<String, Object>>> releaseInventory(
            @RequestHeader(value = "X-User", required = false) String xUser,
            @RequestBody InventoryOperationRequest request) {
        return ResponseEntity.ok(inventoryOperationService.releaseInventory(request, resolvePrincipal(xUser)));
    }

    private String resolvePrincipal(String xUser) {
        if (xUser == null || !xUser.contains(":")) {
            return "system";
        }
        return xUser.split(":", 2)[0];
    }
}
