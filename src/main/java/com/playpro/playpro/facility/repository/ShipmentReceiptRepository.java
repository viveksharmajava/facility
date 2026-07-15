package com.playpro.playpro.facility.repository;

import com.playpro.playpro.facility.entity.ShipmentReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ShipmentReceiptRepository extends JpaRepository<ShipmentReceipt, String> {

    List<ShipmentReceipt> findByProductId(String productId);

    @Query("SELECT COALESCE(SUM(sr.quantityAccepted), 0) FROM ShipmentReceipt sr "
            + "WHERE sr.orderId = :orderId AND sr.productId = :productId AND sr.orderItemSeqId = :orderItemSeqId")
    BigDecimal sumAcceptedByOrderLine(@Param("orderId") String orderId,
                                      @Param("productId") String productId,
                                      @Param("orderItemSeqId") String orderItemSeqId);
}
