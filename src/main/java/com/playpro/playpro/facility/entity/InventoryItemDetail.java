package com.playpro.playpro.facility.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_item_detail")
public class InventoryItemDetail {

    @EmbeddedId
    private InventoryItemDetailId id;

    @Column(name = "effective_date")
    private LocalDateTime effectiveDate;

    @Column(name = "quantity_on_hand_diff", precision = 18, scale = 3)
    private BigDecimal quantityOnHandDiff;

    @Column(name = "available_to_promise_diff", precision = 18, scale = 3)
    private BigDecimal availableToPromiseDiff;

    @Column(name = "accounting_quantity_diff", precision = 18, scale = 3)
    private BigDecimal accountingQuantityDiff;

    @Column(name = "unit_cost", precision = 18, scale = 3)
    private BigDecimal unitCost;

    @Column(name = "order_id", length = 20)
    private String orderId;

    @Column(name = "order_item_seq_id", length = 20)
    private String orderItemSeqId;

    @Column(name = "ship_group_seq_id", length = 20)
    private String shipGroupSeqId;

    @Column(name = "shipment_id", length = 20)
    private String shipmentId;

    @Column(name = "shipment_item_seq_id", length = 20)
    private String shipmentItemSeqId;

    @Column(name = "return_id", length = 20)
    private String returnId;

    @Column(name = "return_item_seq_id", length = 20)
    private String returnItemSeqId;

    @Column(name = "work_effort_id", length = 20)
    private String workEffortId;

    @Column(name = "fixed_asset_id", length = 20)
    private String fixedAssetId;

    @Column(name = "maint_hist_seq_id", length = 20)
    private String maintHistSeqId;

    @Column(name = "item_issuance_id", length = 20)
    private String itemIssuanceId;

    @Column(name = "receipt_id", length = 20)
    private String receiptId;

    @Column(name = "physical_inventory_id", length = 20)
    private String physicalInventoryId;

    @Column(name = "reason_enum_id", length = 20)
    private String reasonEnumId;

    @Column(length = 255)
    private String description;

    public InventoryItemDetailId getId() {
        return id;
    }

    public void setId(InventoryItemDetailId id) {
        this.id = id;
    }

    public LocalDateTime getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDateTime effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public BigDecimal getQuantityOnHandDiff() {
        return quantityOnHandDiff;
    }

    public void setQuantityOnHandDiff(BigDecimal quantityOnHandDiff) {
        this.quantityOnHandDiff = quantityOnHandDiff;
    }

    public BigDecimal getAvailableToPromiseDiff() {
        return availableToPromiseDiff;
    }

    public void setAvailableToPromiseDiff(BigDecimal availableToPromiseDiff) {
        this.availableToPromiseDiff = availableToPromiseDiff;
    }

    public BigDecimal getAccountingQuantityDiff() {
        return accountingQuantityDiff;
    }

    public void setAccountingQuantityDiff(BigDecimal accountingQuantityDiff) {
        this.accountingQuantityDiff = accountingQuantityDiff;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
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

    public String getShipGroupSeqId() {
        return shipGroupSeqId;
    }

    public void setShipGroupSeqId(String shipGroupSeqId) {
        this.shipGroupSeqId = shipGroupSeqId;
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

    public String getWorkEffortId() {
        return workEffortId;
    }

    public void setWorkEffortId(String workEffortId) {
        this.workEffortId = workEffortId;
    }

    public String getFixedAssetId() {
        return fixedAssetId;
    }

    public void setFixedAssetId(String fixedAssetId) {
        this.fixedAssetId = fixedAssetId;
    }

    public String getMaintHistSeqId() {
        return maintHistSeqId;
    }

    public void setMaintHistSeqId(String maintHistSeqId) {
        this.maintHistSeqId = maintHistSeqId;
    }

    public String getItemIssuanceId() {
        return itemIssuanceId;
    }

    public void setItemIssuanceId(String itemIssuanceId) {
        this.itemIssuanceId = itemIssuanceId;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public String getPhysicalInventoryId() {
        return physicalInventoryId;
    }

    public void setPhysicalInventoryId(String physicalInventoryId) {
        this.physicalInventoryId = physicalInventoryId;
    }

    public String getReasonEnumId() {
        return reasonEnumId;
    }

    public void setReasonEnumId(String reasonEnumId) {
        this.reasonEnumId = reasonEnumId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
