-- liquibase formatted sql
-- changeset zbiir:1
CREATE TABLE CUSTOMERS (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    Name varchar(50) NOT NULL,
    Addres varchar(50),
    Phone varchar(20),
    PRIMARY KEY (ID)
)