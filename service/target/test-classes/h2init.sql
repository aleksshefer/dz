create table if not exists accounts
(
    id      bigint auto_increment primary key,
    name    varchar(255),
    balance int
)