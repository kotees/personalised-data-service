CREATE TABLE PRODUCT_METADATA
(
    product_id varchar(255) PRIMARY KEY,
    category   varchar(255),
    brand      varchar(255)
);

CREATE TABLE SHOPPER
(
    shopper_id varchar(255) PRIMARY KEY,
    name       varchar(255),
    address    varchar(255)
);

CREATE TABLE PRODUCT_PERSONALIZATION
(
    id              varchar(255) PRIMARY KEY,
    relevancy_score DOUBLE PRECISION,
    product_id      varchar REFERENCES PRODUCT_METADATA (product_id),
    shopper_id      varchar REFERENCES SHOPPER (shopper_id)
);