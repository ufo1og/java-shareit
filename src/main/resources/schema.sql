CREATE TABLE IF NOT EXISTS USERS (
    id BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
    name VARCHAR(32) NOT NULL,
    email VARCHAR(32) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id),
    CONSTRAINT uq_user_email UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS ITEMS (
    id BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
    name VARCHAR(32) NOT NULL,
    description VARCHAR(256) NOT NULL,
    is_available BOOLEAN NOT NULL,
    owner_id BIGINT NOT NULL,
    CONSTRAINT pk_item PRIMARY KEY (id),
    CONSTRAINT fk_owner_id FOREIGN KEY (owner_id) REFERENCES USERS (id)
);

CREATE TABLE IF NOT EXISTS BOOKINGS (
    id BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    status VARCHAR(16) NOT NULL,
    item_id BIGINT NOT NULL,
    booker_id BIGINT NOT NULL,
    CONSTRAINT pk_booking PRIMARY KEY (id),
    CONSTRAINT fk_item_id FOREIGN KEY (item_id) REFERENCES ITEMS (id),
    CONSTRAINT fk_booker_id FOREIGN KEY (booker_id) REFERENCES USERS (id)
);