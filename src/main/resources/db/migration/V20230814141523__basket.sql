create table basket(
    order_id BIGSERIAL,
    good_id BIGSERIAL);
ALTER TABLE basket
     ADD CONSTRAINT fk_order_available
        FOREIGN KEY (order_id) REFERENCES orders (id);
ALTER TABLE basket
    ADD CONSTRAINT fk_good_available
        FOREIGN KEY (good_id) REFERENCES goods1(id);
