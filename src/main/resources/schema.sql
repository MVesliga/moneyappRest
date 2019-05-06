create table if not exists Users(
id identity,
username varchar(20) not null,
password varchar(255) not null,
enabled boolean not null
);

create table if not exists Authorities(
id identity,
username varchar(20) not null,
authority varchar(20) not null
);

create table if not exists Wallets(
id identity,
create_date timestamp not null,
wallet_name varchar(50) not null,
wallet_type varchar(10) not null,
user_name varchar(20)
);

create table if not exists Expenses(
id identity,
create_date timestamp not null,
expense_name varchar(50) not null,
amount double not null,
expense_type varchar(20) not null,
wallet_id int,
FOREIGN KEY(wallet_id) REFERENCES Wallets(id)
);


