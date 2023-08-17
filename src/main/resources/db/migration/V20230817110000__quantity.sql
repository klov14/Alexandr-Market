alter table basket
add column quantity double precision;
alter table basket
add column id BIGSERIAL PRIMARY KEY;