Alter table countries
    ADD COLUMN created_user varchar;
Alter table countries
    ADD COLUMN created_date timestamp;
Alter table countries
    ADD COLUMN updated_user varchar;
Alter table countries
    ADD COLUMN updated_date timestamp;

Alter table category
    ADD COLUMN created_user varchar;
Alter table category
    ADD COLUMN created_date timestamp;
Alter table category
    ADD COLUMN updated_user varchar;
Alter table category
    ADD COLUMN updated_date timestamp;

INSERT INTO countries (code, nameof) VALUES ('USA', 'United States of America');
INSERT INTO countries (code, nameof) VALUES ('CAN', 'Canada');
INSERT INTO countries (code, nameof) VALUES ('RU', 'Russian Federation');
INSERT INTO countries (code, nameof) VALUES ('KZ', 'Kazakhstan');
INSERT INTO countries (code, nameof) VALUES ('KG', 'Kirghistan');
INSERT INTO countries (code, nameof) VALUES ('UK', 'United Kingdom');
INSERT INTO countries (code, nameof) VALUES ('MX', 'Mexico');
INSERT INTO countries (code, nameof) VALUES ('AZ', 'Azerbaijan');

INSERT INTO category (name_category) VALUES ('Fish');
INSERT INTO category (name_category) VALUES ('Fruit');
INSERT INTO category (name_category) VALUES ('Veg');
INSERT INTO category (name_category) VALUES ('Berries');
INSERT INTO category (name_category) VALUES ('Drinks');
INSERT INTO category (name_category) VALUES ('Meat');
INSERT INTO category (name_category) VALUES ('Fish');