-- OFBiz facility and inventory model (product-entitymodel.xml, shipment-entitymodel.xml)

CREATE TABLE status_type (
    status_type_id  VARCHAR(20) NOT NULL PRIMARY KEY,
    parent_type_id  VARCHAR(20),
    has_table       CHAR(1),
    description     VARCHAR(255)
);

CREATE TABLE status_item (
    status_id       VARCHAR(20) NOT NULL PRIMARY KEY,
    status_type_id  VARCHAR(20),
    status_code     VARCHAR(60),
    sequence_id     VARCHAR(20),
    description     VARCHAR(255),
    CONSTRAINT fk_status_item_type FOREIGN KEY (status_type_id) REFERENCES status_type (status_type_id)
);

CREATE TABLE facility_type (
    facility_type_id VARCHAR(20) NOT NULL PRIMARY KEY,
    parent_type_id   VARCHAR(20),
    has_table        CHAR(1),
    description      VARCHAR(255)
);

CREATE TABLE facility_group_type (
    facility_group_type_id VARCHAR(20) NOT NULL PRIMARY KEY,
    description            VARCHAR(255)
);

CREATE TABLE facility_group (
    facility_group_id        VARCHAR(20) NOT NULL PRIMARY KEY,
    facility_group_type_id   VARCHAR(20),
    primary_parent_group_id  VARCHAR(20),
    facility_group_name      VARCHAR(100),
    description              VARCHAR(255),
    CONSTRAINT fk_facility_group_type FOREIGN KEY (facility_group_type_id) REFERENCES facility_group_type (facility_group_type_id)
);

CREATE TABLE facility (
    facility_id                    VARCHAR(20) NOT NULL PRIMARY KEY,
    facility_type_id               VARCHAR(20) NOT NULL,
    parent_facility_id             VARCHAR(20),
    owner_party_id                 VARCHAR(20),
    default_inventory_item_type_id VARCHAR(20),
    facility_name                  VARCHAR(100),
    primary_facility_group_id      VARCHAR(20),
    facility_size                  NUMERIC(18, 3),
    facility_size_uom_id           VARCHAR(20),
    product_store_id               VARCHAR(20),
    default_days_to_ship           NUMERIC(20, 0),
    opened_date                    TIMESTAMP,
    closed_date                    TIMESTAMP,
    description                    VARCHAR(255),
    default_dimension_uom_id       VARCHAR(20),
    default_weight_uom_id          VARCHAR(20),
    geo_point_id                   VARCHAR(20),
    facility_level                 NUMERIC(20, 0),
    CONSTRAINT fk_facility_type FOREIGN KEY (facility_type_id) REFERENCES facility_type (facility_type_id)
);

CREATE TABLE facility_location (
    facility_id          VARCHAR(20) NOT NULL,
    location_seq_id      VARCHAR(20) NOT NULL,
    location_type_enum_id VARCHAR(20),
    area_id              VARCHAR(20),
    aisle_id             VARCHAR(20),
    section_id           VARCHAR(20),
    level_id             VARCHAR(20),
    position_id          VARCHAR(20),
    geo_point_id         VARCHAR(20),
    PRIMARY KEY (facility_id, location_seq_id),
    CONSTRAINT fk_facility_location_facility FOREIGN KEY (facility_id) REFERENCES facility (facility_id)
);

CREATE TABLE product_store_facility (
    product_store_id VARCHAR(20) NOT NULL,
    facility_id      VARCHAR(20) NOT NULL,
    from_date        TIMESTAMP NOT NULL,
    thru_date        TIMESTAMP,
    sequence_num     NUMERIC(20, 0),
    PRIMARY KEY (product_store_id, facility_id, from_date),
    CONSTRAINT fk_psf_facility FOREIGN KEY (facility_id) REFERENCES facility (facility_id)
);

CREATE TABLE product_facility (
    product_id               VARCHAR(20) NOT NULL,
    facility_id              VARCHAR(20) NOT NULL,
    minimum_stock            NUMERIC(18, 3),
    reorder_quantity         NUMERIC(18, 3),
    days_to_ship             NUMERIC(20, 0),
    replenish_method_enum_id VARCHAR(20),
    last_inventory_count     NUMERIC(18, 3),
    requirement_method_enum_id VARCHAR(20),
    PRIMARY KEY (product_id, facility_id),
    CONSTRAINT fk_pf_facility FOREIGN KEY (facility_id) REFERENCES facility (facility_id)
);

CREATE TABLE inventory_item_type (
    inventory_item_type_id VARCHAR(20) NOT NULL PRIMARY KEY,
    parent_type_id         VARCHAR(20),
    has_table              CHAR(1),
    description            VARCHAR(255)
);

CREATE TABLE inventory_item (
    inventory_item_id        VARCHAR(20) NOT NULL PRIMARY KEY,
    inventory_item_type_id   VARCHAR(20) NOT NULL,
    product_id               VARCHAR(20),
    party_id                 VARCHAR(20),
    owner_party_id           VARCHAR(20),
    status_id                VARCHAR(20),
    datetime_received        TIMESTAMP,
    datetime_manufactured    TIMESTAMP,
    expire_date              TIMESTAMP,
    facility_id              VARCHAR(20),
    container_id             VARCHAR(20),
    lot_id                   VARCHAR(20),
    uom_id                   VARCHAR(20),
    bin_number               VARCHAR(20),
    location_seq_id          VARCHAR(20),
    comments                 VARCHAR(255),
    quantity_on_hand_total   NUMERIC(18, 3),
    available_to_promise_total NUMERIC(18, 3),
    accounting_quantity_total NUMERIC(18, 3),
    serial_number            VARCHAR(255),
    soft_identifier          VARCHAR(255),
    activation_number        VARCHAR(255),
    activation_valid_thru    TIMESTAMP,
    unit_cost                NUMERIC(18, 3),
    currency_uom_id          VARCHAR(20),
    fixed_asset_id           VARCHAR(20),
    CONSTRAINT fk_inv_item_type FOREIGN KEY (inventory_item_type_id) REFERENCES inventory_item_type (inventory_item_type_id),
    CONSTRAINT fk_inv_item_facility FOREIGN KEY (facility_id) REFERENCES facility (facility_id),
    CONSTRAINT fk_inv_item_status FOREIGN KEY (status_id) REFERENCES status_item (status_id)
);

CREATE TABLE inventory_item_detail (
    inventory_item_id            VARCHAR(20) NOT NULL,
    inventory_item_detail_seq_id VARCHAR(20) NOT NULL,
    effective_date               TIMESTAMP,
    quantity_on_hand_diff        NUMERIC(18, 3),
    available_to_promise_diff    NUMERIC(18, 3),
    accounting_quantity_diff     NUMERIC(18, 3),
    unit_cost                    NUMERIC(18, 3),
    order_id                     VARCHAR(20),
    order_item_seq_id            VARCHAR(20),
    ship_group_seq_id            VARCHAR(20),
    shipment_id                  VARCHAR(20),
    shipment_item_seq_id         VARCHAR(20),
    return_id                    VARCHAR(20),
    return_item_seq_id           VARCHAR(20),
    work_effort_id               VARCHAR(20),
    fixed_asset_id               VARCHAR(20),
    maint_hist_seq_id            VARCHAR(20),
    item_issuance_id             VARCHAR(20),
    receipt_id                   VARCHAR(20),
    physical_inventory_id        VARCHAR(20),
    reason_enum_id               VARCHAR(20),
    description                  VARCHAR(255),
    PRIMARY KEY (inventory_item_id, inventory_item_detail_seq_id),
    CONSTRAINT fk_inv_item_detail_item FOREIGN KEY (inventory_item_id) REFERENCES inventory_item (inventory_item_id)
);

CREATE TABLE inventory_transfer (
    inventory_transfer_id VARCHAR(20) NOT NULL PRIMARY KEY,
    status_id             VARCHAR(20),
    inventory_item_id     VARCHAR(20),
    facility_id           VARCHAR(20),
    location_seq_id       VARCHAR(20),
    container_id          VARCHAR(20),
    facility_id_to        VARCHAR(20),
    location_seq_id_to    VARCHAR(20),
    container_id_to       VARCHAR(20),
    item_issuance_id      VARCHAR(20),
    send_date             TIMESTAMP,
    receive_date          TIMESTAMP,
    comments              VARCHAR(255),
    CONSTRAINT fk_inv_xfer_item FOREIGN KEY (inventory_item_id) REFERENCES inventory_item (inventory_item_id),
    CONSTRAINT fk_inv_xfer_status FOREIGN KEY (status_id) REFERENCES status_item (status_id),
    CONSTRAINT fk_inv_xfer_facility FOREIGN KEY (facility_id) REFERENCES facility (facility_id),
    CONSTRAINT fk_inv_xfer_facility_to FOREIGN KEY (facility_id_to) REFERENCES facility (facility_id)
);

CREATE TABLE shipment_receipt (
    receipt_id              VARCHAR(20) NOT NULL PRIMARY KEY,
    inventory_item_id       VARCHAR(20),
    product_id              VARCHAR(20),
    shipment_id             VARCHAR(20),
    shipment_item_seq_id    VARCHAR(20),
    shipment_package_seq_id VARCHAR(20),
    order_id                VARCHAR(20),
    order_item_seq_id       VARCHAR(20),
    return_id               VARCHAR(20),
    return_item_seq_id      VARCHAR(20),
    rejection_id            VARCHAR(20),
    received_by_user_login_id VARCHAR(250),
    datetime_received       TIMESTAMP,
    item_description        VARCHAR(255),
    quantity_accepted       NUMERIC(18, 3),
    quantity_rejected       NUMERIC(18, 3),
    CONSTRAINT fk_ship_receipt_item FOREIGN KEY (inventory_item_id) REFERENCES inventory_item (inventory_item_id)
);

CREATE INDEX idx_inventory_item_facility ON inventory_item (facility_id, product_id);
CREATE INDEX idx_inventory_item_detail_receipt ON inventory_item_detail (receipt_id);
CREATE INDEX idx_product_store_facility_store ON product_store_facility (product_store_id);
CREATE INDEX idx_product_store_facility_facility ON product_store_facility (facility_id);
