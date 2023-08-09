create table contacts(
                id BIGSERIAL PRIMARY KEY,
                mobile TEXT,
                telephone TEXT,
                user_id INTEGER);
create table address(
              id BIGSERIAL PRIMARY KEY,
              house_number TEXT,
              street_name TEXT,
              postcode TEXT,
              city TEXT);

ALTER TABLE contacts
    ADD CONSTRAINT fk_contacts_user
        FOREIGN KEY (user_id) REFERENCES user1 (id);