create table countries (
                           id BIGSERIAL PRIMARY KEY,
                           code TEXT,
                           nameof TEXT);

create table category (
  id BIGSERIAL PRIMARY KEY,
  name_category TEXT
);

CREATE TABLE goods1 (
                       id BIGSERIAL PRIMARY KEY,
                       product TEXT,
                       price DOUBLE PRECISION,
                       weight DOUBLE PRECISION,
                       country_id INTEGER,
                       category_id INTEGER);
ALTER TABLE goods1
ADD CONSTRAINT fk_goods_country
FOREIGN KEY (country_id) REFERENCES countries (id);
ALTER TABLE goods1
    ADD CONSTRAINT fk_goods_category
        FOREIGN KEY (category_id) REFERENCES category (id);

create table countries_available (
    id BIGSERIAL PRIMARY KEY,
    country_id INTEGER,
    category_id INTEGER
);
ALTER TABLE countries_available
    ADD CONSTRAINT fk_countries_available
        FOREIGN KEY (country_id) REFERENCES countries(id);
ALTER TABLE countries_available
    ADD CONSTRAINT fk_categories_available
        FOREIGN KEY (category_id) REFERENCES category (id);



