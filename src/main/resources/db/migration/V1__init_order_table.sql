create table if not exists orders (
    id uuid not null,
    status varchar not null,
    amount double precision not null,
    create_at timestamp not null,
    primary key(id)
);
