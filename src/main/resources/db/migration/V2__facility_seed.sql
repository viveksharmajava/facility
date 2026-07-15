-- Reference and demo seed data

INSERT INTO status_type (status_type_id, parent_type_id, has_table, description) VALUES
    ('INVENTORY_ITEM_STTS', NULL, 'N', 'Inventory Item'),
    ('INV_SERIALIZED_STTS', 'INVENTORY_ITEM_STTS', 'N', 'Serialized Inventory Item'),
    ('INV_NON_SER_STTS', 'INVENTORY_ITEM_STTS', 'N', 'Non-Serialized Inventory Item'),
    ('INVENTORY_XFER_STTS', NULL, 'N', 'Inventory Transfer');

INSERT INTO status_item (status_id, status_type_id, status_code, sequence_id, description) VALUES
    ('INV_ON_ORDER', 'INV_SERIALIZED_STTS', 'ON_ORDER', '01', 'On Order'),
    ('INV_AVAILABLE', 'INV_SERIALIZED_STTS', 'AVAILABLE', '02', 'Available'),
    ('INV_PROMISED', 'INV_SERIALIZED_STTS', 'PROMISED', '03', 'Promised'),
    ('INV_BEING_TRANSFERED', 'INV_SERIALIZED_STTS', 'BEING_TRANSFERED', '10', 'Being Transfered'),
    ('INV_NS_ON_HOLD', 'INV_NON_SER_STTS', 'ON_HOLD_NS', '01', 'On Hold (Non-Serialized)'),
    ('IXF_REQUESTED', 'INVENTORY_XFER_STTS', 'REQUESTED', '01', 'Requested'),
    ('IXF_SCHEDULED', 'INVENTORY_XFER_STTS', 'SCHEDULED', '02', 'Scheduled'),
    ('IXF_EN_ROUTE', 'INVENTORY_XFER_STTS', 'EN_ROUTE', '03', 'En-Route'),
    ('IXF_COMPLETE', 'INVENTORY_XFER_STTS', 'COMPLETE', '04', 'Complete'),
    ('IXF_CANCELLED', 'INVENTORY_XFER_STTS', 'CANCELLED', '99', 'Cancelled');

INSERT INTO facility_type (facility_type_id, parent_type_id, has_table, description) VALUES
    ('WAREHOUSE', NULL, 'N', 'Warehouse'),
    ('RETAIL_STORE', NULL, 'N', 'Retail Store'),
    ('DISTRIBUTION_CENTER', NULL, 'N', 'Distribution Center');

INSERT INTO inventory_item_type (inventory_item_type_id, parent_type_id, has_table, description) VALUES
    ('NON_SERIAL_INV_ITEM', NULL, 'N', 'Non-Serialized'),
    ('SERIALIZED_INV_ITEM', NULL, 'N', 'Serialized');

INSERT INTO facility (
    facility_id,
    facility_type_id,
    default_inventory_item_type_id,
    facility_name,
    description,
    opened_date
) VALUES (
    'DEMO_WAREHOUSE',
    'WAREHOUSE',
    'NON_SERIAL_INV_ITEM',
    'Demo Warehouse',
    'Demo warehouse facility for development',
    CURRENT_TIMESTAMP
);

INSERT INTO facility_location (facility_id, location_seq_id, area_id, aisle_id) VALUES
    ('DEMO_WAREHOUSE', 'DEFAULT', 'A1', '01');
