CREATE TABLE address_available(
    user_id BIGSERIAL,
    delivery_id BIGSERIAL
);
ALTER TABLE address_available
    ADD CONSTRAINT fk_users_available
        FOREIGN KEY (user_id) REFERENCES user1 (id);
ALTER TABLE address_available
    ADD CONSTRAINT fk_delivery_available
        FOREIGN KEY (delivery_id) REFERENCES address(id);