-- liquibase formatted sql

-- changeset zbiir:8
ALTER TABLE unit_order
ADD COLUMN tag_label_no varchar(6) DEFAULT "";

ALTER TABLE unit_order
MODIFY COLUMN tag_label varchar(5);

-- changeset zbiir:9
ALTER TABLE unit_order_archive
    ADD COLUMN tag_label_no varchar(6) DEFAULT "";

ALTER TABLE unit_order_archive
    MODIFY COLUMN tag_label varchar(5);


-- changeset zbiir:10
ALTER TABLE order_archive
ADD COLUMN IS_PAID BOOLEAN DEFAULT false;

