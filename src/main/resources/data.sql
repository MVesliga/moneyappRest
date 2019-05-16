insert into users (username,password,enabled) values ('admin','$2a$12$YunQ3xQT.WT7iJpWS7ic/.yrsdPzhg/asmwRu3pQ.4OWaP.EG2CRK',1);
insert into users (username,password,enabled) values ('student','$2a$12$WYCJYzj6qV75ibzCdrL3IexcCd40JvaIPk6G0G/OzjoWZBIn6c.DO',1);

insert into authorities (username, authority) values ('admin','ROLE_ADMIN');
insert into authorities (username,authority) values ('admin','ROLE_USER');
insert into authorities (username,authority) values ('student','ROLE_USER');

insert into wallets (create_date,wallet_name,wallet_type,user_name) values ('2019-05-15 10:45:00','Admin wallet', 'Gotovina', 'admin');
/*insert into wallets (create_date,wallet_name,wallet_type,user_name) values ('2019-05-15 10:45:49','Student wallet', 'Gotovina', 'student');*/

insert into expenses (create_date,expense_name,amount,expense_type,wallet_id) values ('2019-05-15 13:45:50','Jagode',15,'Hrana',1);
