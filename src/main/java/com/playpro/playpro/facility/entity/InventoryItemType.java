package com.playpro.playpro.facility.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inventory_item_type")
public class InventoryItemType {

    @Id
    @Column(name = "inventory_item_type_id", length = 20)
    private String inventoryItemTypeId;

    @Column(name = "parent_type_id", length = 20)
    private String parentTypeId;

    @Column(name = "has_table", length = 1)
    private String hasTable;

    @Column(length = 255)
    private String description;

    public String getInventoryItemTypeId() {
        return inventoryItemTypeId;
    }

    public void setInventoryItemTypeId(String inventoryItemTypeId) {
        this.inventoryItemTypeId = inventoryItemTypeId;
    }

    public String getParentTypeId() {
        return parentTypeId;
    }

    public void setParentTypeId(String parentTypeId) {
        this.parentTypeId = parentTypeId;
    }

    public String getHasTable() {
        return hasTable;
    }

    public void setHasTable(String hasTable) {
        this.hasTable = hasTable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
