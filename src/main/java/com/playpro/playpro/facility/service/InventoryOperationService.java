package com.playpro.playpro.facility.service;

import com.playpro.playpro.facility.dto.InventoryOperationRequest;

import java.util.List;
import java.util.Map;

public interface InventoryOperationService {

    List<Map<String, Object>> reserveInventory(InventoryOperationRequest request, String principal);

    List<Map<String, Object>> issueInventory(InventoryOperationRequest request, String principal);

    List<Map<String, Object>> releaseInventory(InventoryOperationRequest request, String principal);
}
