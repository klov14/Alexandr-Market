create table countries (
                           id BIGSERIAL PRIMARY KEY,
                           code TEXT,
                           nameof TEXT);

CREATE TABLE goods1 (
                       id BIGSERIAL PRIMARY KEY,
                       product TEXT,
                       price DOUBLE PRECISION,
                       weight DOUBLE PRECISION,
                       country_id INTEGER);

ALTER TABLE goods1
ADD CONSTRAINT fk_goods_country
FOREIGN KEY (country_id) REFERENCES countries (id);