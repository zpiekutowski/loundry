-- liquibase formatted sql
-- changeset zbiir:1
CREATE TABLE CUSTOMERS (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    Name varchar(50) NOT NULL,
    Addres varchar(50),
    Phone varchar(20),
    PRIMARY KEY (ID)
)

-- changeset zbiir:2
CREATE TABLE SERVICE_UNIT (
     ID BIGINT NOT NULL AUTO_INCREMENT,
    DESCRYPTION varchar(50) NOT NULL,
     PRIMARY KEY (ID)
)
-- changeset zbiir:3
ALTER TABLE SERVICE_UNIT RENAME SERVED_UNIT;
