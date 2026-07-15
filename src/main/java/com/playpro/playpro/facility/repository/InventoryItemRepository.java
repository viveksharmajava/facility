package com.playpro.playpro.facility.repository;

import com.playpro.playpro.facility.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, String> {

    List<InventoryItem> findByFacilityId(String facilityId);

    Optional<InventoryItem> findByFacilityIdAndProductIdAndLocationSeqId(String facilityId, String productId, String locationSeqId);

    List<InventoryItem> findByProductId(String productId);

    List<InventoryItem> findByFacilityIdInAndProductId(List<String> facilityIds, String productId);

    List<InventoryItem> findByFacilityIdInAndProductIdIn(List<String> facilityIds, List<String> productIds);

    List<InventoryItem> findByFacilityIdIn(List<String> facilityIds);
}
