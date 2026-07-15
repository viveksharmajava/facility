package com.playpro.playpro.facility.service;

import com.playpro.playpro.facility.dto.PurchaseOrderReceiptDto;
import com.playpro.playpro.facility.dto.ReceiveInventoryRequest;

import java.util.List;
import java.util.Map;

public interface ReceiveInventoryService {

    PurchaseOrderReceiptDto getPurchaseOrderForReceipt(String facilityId, String orderId, String xUser);

    List<Map<String, Object>> receiveInventory(String facilityId, ReceiveInventoryRequest request, String principal);
}
