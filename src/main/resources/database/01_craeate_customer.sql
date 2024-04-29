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
CREATE TABLE SERVED_UNIT (
     ID BIGINT NOT NULL AUTO_INCREMENT,
    DESCRYPTION varchar(50) NOT NULL,
     PRIMARY KEY (ID)
)
--
-- -- changeset zbiir:3
-- INSERT INTO CUSTOMERS(Name,Addres,Phone) VALUES ('Krzysztof Tracz','Prusa 34','639 985 125');
-- INSERT INTO CUSTOMERS(Name,Addres,Phone) VALUES ('Grzegorz Kapusta','Filkowa 167','523 781 326');
-- INSERT INTO CUSTOMERS(Name,Addres,Phone) VALUES ('Alicja Kowalska','Mickiweicza 85','845 369 521');
-- INSERT INTO CUSTOMERS(Name,Addres,Phone) VALUES ('Waldemar Nowak','Szastarka 456','955 654 668');
--
-- INSERT INTO SERVED_UNIT(DESCRYPTION) VALUES ('Wodne Biale');
-- INSERT INTO SERVED_UNIT(DESCRYPTION) VALUES ('Wodne Kolorowe');
-- INSERT INTO SERVED_UNIT(DESCRYPTION) VALUES ('Chemiczne');
-- INSERT INTO SERVED_UNIT(DESCRYPTION) VALUES ('Dywany');
-- INSERT INTO SERVED_UNIT(DESCRYPTION) VALUES ('Magiel');


