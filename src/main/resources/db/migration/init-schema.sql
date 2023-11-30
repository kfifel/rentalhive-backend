CREATE TABLE contract
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    conditions     TEXT                  NULL,
    reservation_id BIGINT                NULL,
    CONSTRAINT pk_contract PRIMARY KEY (id)
);

CREATE TABLE edocument
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    model_name VARCHAR(255)          NULL,
    model_id   BIGINT                NULL,
    classpath  TEXT                  NULL,
    CONSTRAINT pk_edocument PRIMARY KEY (id)
);

CREATE TABLE equipment
(
    id                  BIGINT AUTO_INCREMENT NOT NULL,
    name                VARCHAR(255)          NULL,
    quantity            INT                   NULL,
    equipment_family_id BIGINT                NULL,
    CONSTRAINT pk_equipment PRIMARY KEY (id)
);

CREATE TABLE equipment_family
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255)          NULL,
    CONSTRAINT pk_equipmentfamily PRIMARY KEY (id)
);

CREATE TABLE equipment_item
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    `reference`  VARCHAR(255)          NULL,
    status       VARCHAR(255)          NULL,
    equipment_id BIGINT                NULL,
    CONSTRAINT pk_equipmentitem PRIMARY KEY (id)
);

CREATE TABLE location
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    name      VARCHAR(255)          NULL,
    latitude  DOUBLE                NULL,
    longitude DOUBLE                NULL,
    CONSTRAINT pk_location PRIMARY KEY (id)
);

CREATE TABLE offer
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    overall_cost DOUBLE                NULL,
    status       VARCHAR(255)          NULL,
    order_id     BIGINT                NULL,
    CONSTRAINT pk_offer PRIMARY KEY (id)
);

CREATE TABLE order_equipment
(
    rent_price        DOUBLE NULL,
    order_id          BIGINT NOT NULL,
    equipment_item_id BIGINT NOT NULL,
    CONSTRAINT pk_orderequipment PRIMARY KEY (order_id, equipment_item_id)
);

CREATE TABLE orders
(
    id                    BIGINT AUTO_INCREMENT NOT NULL,
    rent_start_date       datetime              NULL,
    rent_end_date         datetime              NULL,
    construct_location_id BIGINT                NULL,
    user_id               BIGINT                NULL,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

CREATE TABLE `organization`
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    name     VARCHAR(255)          NULL,
    since    INT                   NULL,
    industry VARCHAR(255)          NULL,
    size     INT                   NULL,
    CONSTRAINT pk_organization PRIMARY KEY (id)
);

CREATE TABLE reservation
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    offer_id BIGINT                NULL,
    CONSTRAINT pk_reservation PRIMARY KEY (id)
);

CREATE TABLE `role`
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255)          NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE user
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    first_name      VARCHAR(255)          NULL,
    last_name       VARCHAR(255)          NULL,
    email           VARCHAR(255)          NULL,
    password        VARCHAR(255)          NULL,
    created_at      datetime              NULL,
    verified_at     datetime              NULL,
    organization_id BIGINT                NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_orders
(
    user_id   BIGINT NOT NULL,
    orders_id BIGINT NOT NULL
);

CREATE TABLE user_roles
(
    user_id  BIGINT NOT NULL,
    roles_id BIGINT NOT NULL
);

ALTER TABLE equipment
    ADD CONSTRAINT uc_equipment_name UNIQUE (name);

ALTER TABLE equipment_family
    ADD CONSTRAINT uc_equipmentfamily_name UNIQUE (name);

ALTER TABLE equipment_item
    ADD CONSTRAINT uc_equipmentitem_reference UNIQUE (`reference`);

ALTER TABLE `organization`
    ADD CONSTRAINT uc_organization_name UNIQUE (name);

ALTER TABLE user_orders
    ADD CONSTRAINT uc_user_orders_orders UNIQUE (orders_id);

ALTER TABLE contract
    ADD CONSTRAINT FK_CONTRACT_ON_RESERVATION FOREIGN KEY (reservation_id) REFERENCES reservation (id);

ALTER TABLE equipment_item
    ADD CONSTRAINT FK_EQUIPMENTITEM_ON_EQUIPMENT FOREIGN KEY (equipment_id) REFERENCES equipment (id);

ALTER TABLE equipment
    ADD CONSTRAINT FK_EQUIPMENT_ON_EQUIPMENTFAMILY FOREIGN KEY (equipment_family_id) REFERENCES equipment_family (id);

ALTER TABLE offer
    ADD CONSTRAINT FK_OFFER_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id);

ALTER TABLE order_equipment
    ADD CONSTRAINT FK_ORDEREQUIPMENT_ON_EQUIPMENTITEM FOREIGN KEY (equipment_item_id) REFERENCES equipment_item (id);

ALTER TABLE order_equipment
    ADD CONSTRAINT FK_ORDEREQUIPMENT_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id);

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_CONSTRUCTLOCATION FOREIGN KEY (construct_location_id) REFERENCES location (id);

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE reservation
    ADD CONSTRAINT FK_RESERVATION_ON_OFFER FOREIGN KEY (offer_id) REFERENCES offer (id);

ALTER TABLE user
    ADD CONSTRAINT FK_USER_ON_ORGANIZATION FOREIGN KEY (organization_id) REFERENCES `organization` (id);

ALTER TABLE user_orders
    ADD CONSTRAINT fk_useord_on_order FOREIGN KEY (orders_id) REFERENCES orders (id);

ALTER TABLE user_orders
    ADD CONSTRAINT fk_useord_on_user FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (roles_id) REFERENCES `role` (id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES user (id);