package com.playpro.playpro.facility.service;

import com.playpro.playpro.facility.dto.CreateInventoryTransferRequest;
import com.playpro.playpro.facility.dto.InventoryTransferDto;

import java.util.List;

public interface InventoryTransferService {

    List<InventoryTransferDto> listByFacility(String facilityId);

    InventoryTransferDto createTransfer(String facilityId, CreateInventoryTransferRequest request, String principal);
}
