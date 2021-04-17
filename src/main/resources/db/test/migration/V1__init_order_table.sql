create table if not exists orders (
    id uuid not null,
    status varchar not null,
    amount double precision not null,
    create_at timestamp not null,
    primary key(id)
);

insert into orders (id, status, amount, create_at)
values ('897c37a2-d59e-4e5c-bef6-df3e5ad26425', 'WAIT_FOR_PAYMENT', 100.0, '2021-04-17 16:00:00.000')
