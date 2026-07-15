package com.playpro.playpro.facility.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_transfer")
public class InventoryTransfer {

    @Id
    @Column(name = "inventory_transfer_id", length = 20)
    private String inventoryTransferId;

    @Column(name = "status_id", length = 20)
    private String statusId;

    @Column(name = "inventory_item_id", length = 20)
    private String inventoryItemId;

    @Column(name = "facility_id", length = 20)
    private String facilityId;

    @Column(name = "location_seq_id", length = 20)
    private String locationSeqId;

    @Column(name = "container_id", length = 20)
    private String containerId;

    @Column(name = "facility_id_to", length = 20)
    private String facilityIdTo;

    @Column(name = "location_seq_id_to", length = 20)
    private String locationSeqIdTo;

    @Column(name = "container_id_to", length = 20)
    private String containerIdTo;

    @Column(name = "item_issuance_id", length = 20)
    private String itemIssuanceId;

    @Column(name = "send_date")
    private LocalDateTime sendDate;

    @Column(name = "receive_date")
    private LocalDateTime receiveDate;

    @Column(length = 255)
    private String comments;

    public String getInventoryTransferId() {
        return inventoryTransferId;
    }

    public void setInventoryTransferId(String inventoryTransferId) {
        this.inventoryTransferId = inventoryTransferId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getInventoryItemId() {
        return inventoryItemId;
    }

    public void setInventoryItemId(String inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getLocationSeqId() {
        return locationSeqId;
    }

    public void setLocationSeqId(String locationSeqId) {
        this.locationSeqId = locationSeqId;
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public String getFacilityIdTo() {
        return facilityIdTo;
    }

    public void setFacilityIdTo(String facilityIdTo) {
        this.facilityIdTo = facilityIdTo;
    }

    public String getLocationSeqIdTo() {
        return locationSeqIdTo;
    }

    public void setLocationSeqIdTo(String locationSeqIdTo) {
        this.locationSeqIdTo = locationSeqIdTo;
    }

    public String getContainerIdTo() {
        return containerIdTo;
    }

    public void setContainerIdTo(String containerIdTo) {
        this.containerIdTo = containerIdTo;
    }

    public String getItemIssuanceId() {
        return itemIssuanceId;
    }

    public void setItemIssuanceId(String itemIssuanceId) {
        this.itemIssuanceId = itemIssuanceId;
    }

    public LocalDateTime getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDateTime sendDate) {
        this.sendDate = sendDate;
    }

    public LocalDateTime getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(LocalDateTime receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
