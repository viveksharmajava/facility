package com.playpro.playpro.facility.service;

import com.playpro.playpro.facility.dto.BulkInventorySummaryRequest;
import com.playpro.playpro.facility.dto.ProductInventoryDetailDto;
import com.playpro.playpro.facility.dto.ProductInventorySummaryDto;

import java.util.List;

public interface InventoryQueryService {

    ProductInventoryDetailDto getProductInventory(String productId, String productStoreId, String xUser);

    List<ProductInventorySummaryDto> summarizeProducts(BulkInventorySummaryRequest request);
}
