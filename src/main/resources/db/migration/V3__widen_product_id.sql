-- Align product_id with catalog (VARCHAR(64)) for inventory operations.

ALTER TABLE inventory_item ALTER COLUMN product_id VARCHAR(64);
ALTER TABLE product_facility ALTER COLUMN product_id VARCHAR(64) NOT NULL;
ALTER TABLE shipment_receipt ALTER COLUMN product_id VARCHAR(64);
