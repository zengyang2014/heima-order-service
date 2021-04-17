create table if not exists order_item (
    id int not null,
    order_id uuid not null,
    product_id uuid not null,
    product_name varchar not null,
    description varchar,
    numbers int not null default 1,
    price double precision not null,
    primary key(id)
);
