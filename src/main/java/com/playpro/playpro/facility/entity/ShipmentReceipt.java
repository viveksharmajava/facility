package com.playpro.playpro.facility.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "shipment_receipt")
public class ShipmentReceipt {

    @Id
    @Column(name = "receipt_id", length = 20)
    private String receiptId;

    @Column(name = "inventory_item_id", length = 20)
    private String inventoryItemId;

    @Column(name = "product_id", length = 20)
    private String productId;

    @Column(name = "shipment_id", length = 20)
    private String shipmentId;

    @Column(name = "shipment_item_seq_id", length = 20)
    private String shipmentItemSeqId;

    @Column(name = "shipment_package_seq_id", length = 20)
    private String shipmentPackageSeqId;

    @Column(name = "order_id", length = 20)
    private String orderId;

    @Column(name = "order_item_seq_id", length = 20)
    private String orderItemSeqId;

    @Column(name = "return_id", length = 20)
    private String returnId;

    @Column(name = "return_item_seq_id", length = 20)
    private String returnItemSeqId;

    @Column(name = "rejection_id", length = 20)
    private String rejectionId;

    @Column(name = "received_by_user_login_id", length = 250)
    private String receivedByUserLoginId;

    @Column(name = "datetime_received")
    private LocalDateTime datetimeReceived;

    @Column(name = "item_description", length = 255)
    private String itemDescription;

    @Column(name = "quantity_accepted", precision = 18, scale = 3)
    private BigDecimal quantityAccepted;

    @Column(name = "quantity_rejected", precision = 18, scale = 3)
    private BigDecimal quantityRejected;

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public String getInventoryItemId() {
        return inventoryItemId;
    }

    public void setInventoryItemId(String inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getShipmentItemSeqId() {
        return shipmentItemSeqId;
    }

    public void setShipmentItemSeqId(String shipmentItemSeqId) {
        this.shipmentItemSeqId = shipmentItemSeqId;
    }

    public String getShipmentPackageSeqId() {
        return shipmentPackageSeqId;
    }

    public void setShipmentPackageSeqId(String shipmentPackageSeqId) {
        this.shipmentPackageSeqId = shipmentPackageSeqId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderItemSeqId() {
        return orderItemSeqId;
    }

    public void setOrderItemSeqId(String orderItemSeqId) {
        this.orderItemSeqId = orderItemSeqId;
    }

    public String getReturnId() {
        return returnId;
    }

    public void setReturnId(String returnId) {
        this.returnId = returnId;
    }

    public String getReturnItemSeqId() {
        return returnItemSeqId;
    }

    public void setReturnItemSeqId(String returnItemSeqId) {
        this.returnItemSeqId = returnItemSeqId;
    }

    public String getRejectionId() {
        return rejectionId;
    }

    public void setRejectionId(String rejectionId) {
        this.rejectionId = rejectionId;
    }

    public String getReceivedByUserLoginId() {
        return receivedByUserLoginId;
    }

    public void setReceivedByUserLoginId(String receivedByUserLoginId) {
        this.receivedByUserLoginId = receivedByUserLoginId;
    }

    public LocalDateTime getDatetimeReceived() {
        return datetimeReceived;
    }

    public void setDatetimeReceived(LocalDateTime datetimeReceived) {
        this.datetimeReceived = datetimeReceived;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public BigDecimal getQuantityAccepted() {
        return quantityAccepted;
    }

    public void setQuantityAccepted(BigDecimal quantityAccepted) {
        this.quantityAccepted = quantityAccepted;
    }

    public BigDecimal getQuantityRejected() {
        return quantityRejected;
    }

    public void setQuantityRejected(BigDecimal quantityRejected) {
        this.quantityRejected = quantityRejected;
    }
}
