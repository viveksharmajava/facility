package com.playpro.playpro.facility.repository;

import com.playpro.playpro.facility.entity.InventoryItemDetail;
import com.playpro.playpro.facility.entity.InventoryItemDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryItemDetailRepository extends JpaRepository<InventoryItemDetail, InventoryItemDetailId> {

    List<InventoryItemDetail> findByOrderId(String orderId);
}
