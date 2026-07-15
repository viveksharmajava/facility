package com.playpro.playpro.facility.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class InventoryItemDetailId implements Serializable {

    @Column(name = "inventory_item_id", length = 20)
    private String inventoryItemId;

    @Column(name = "inventory_item_detail_seq_id", length = 20)
    private String inventoryItemDetailSeqId;

    public InventoryItemDetailId() {
    }

    public InventoryItemDetailId(String inventoryItemId, String inventoryItemDetailSeqId) {
        this.inventoryItemId = inventoryItemId;
        this.inventoryItemDetailSeqId = inventoryItemDetailSeqId;
    }

    public String getInventoryItemId() {
        return inventoryItemId;
    }

    public void setInventoryItemId(String inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
    }

    public String getInventoryItemDetailSeqId() {
        return inventoryItemDetailSeqId;
    }

    public void setInventoryItemDetailSeqId(String inventoryItemDetailSeqId) {
        this.inventoryItemDetailSeqId = inventoryItemDetailSeqId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InventoryItemDetailId)) return false;
        InventoryItemDetailId that = (InventoryItemDetailId) o;
        return Objects.equals(inventoryItemId, that.inventoryItemId)
                && Objects.equals(inventoryItemDetailSeqId, that.inventoryItemDetailSeqId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryItemId, inventoryItemDetailSeqId);
    }
}
