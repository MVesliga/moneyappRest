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
createDate timestamp not null,
walletName varchar(50) not null,
walletType varchar(10) not null,
userName varchar(20)
);

create table if not exists Expenses(
id identity,
createDate timestamp not null,
expenseName varchar(50) not null,
amount double not null,
expenseType varchar(20) not null,
walletId int,
FOREIGN KEY(walletId) REFERENCES Wallets(id)
);


