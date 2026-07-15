package com.playpro.playpro.facility.controller;

import com.playpro.playpro.facility.dto.CreateInventoryTransferRequest;
import com.playpro.playpro.facility.dto.InventoryTransferDto;
import com.playpro.playpro.facility.service.InventoryTransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/facility/facilities/{facilityId}/inventory-transfers")
public class InventoryTransferController {

    private final InventoryTransferService inventoryTransferService;

    public InventoryTransferController(InventoryTransferService inventoryTransferService) {
        this.inventoryTransferService = inventoryTransferService;
    }

    @GetMapping
    public ResponseEntity<List<InventoryTransferDto>> listTransfers(@PathVariable String facilityId) {
        return ResponseEntity.ok(inventoryTransferService.listByFacility(facilityId));
    }

    @PostMapping
    public ResponseEntity<InventoryTransferDto> createTransfer(@RequestHeader(value = "X-User", required = false) String xUser,
                                                               @PathVariable String facilityId,
                                                               @RequestBody CreateInventoryTransferRequest request) {
        return ResponseEntity.ok(inventoryTransferService.createTransfer(facilityId, request, resolvePrincipal(xUser)));
    }

    private String resolvePrincipal(String xUser) {
        if (xUser == null || !xUser.contains(":")) {
            return "system";
        }
        return xUser.split(":", 2)[0];
    }
}
