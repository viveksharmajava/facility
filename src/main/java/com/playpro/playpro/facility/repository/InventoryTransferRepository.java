package com.playpro.playpro.facility.repository;

import com.playpro.playpro.facility.entity.InventoryTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryTransferRepository extends JpaRepository<InventoryTransfer, String> {

    List<InventoryTransfer> findByFacilityIdOrFacilityIdToOrderBySendDateDesc(String facilityId, String facilityIdTo);
}
