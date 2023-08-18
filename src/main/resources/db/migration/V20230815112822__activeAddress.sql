alter table address
Add COLUMN active boolean DEFAULT false;
alter table orders
    add column active_user TEXT;
alter table orders
add column address_id integer;
ALTER TABLE orders
    ADD CONSTRAINT fk_order_address
        FOREIGN KEY (address_id) REFERENCES address (id);