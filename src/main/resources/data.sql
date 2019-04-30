insert into users (username,password,enabled) values ('admin','$2a$12$YunQ3xQT.WT7iJpWS7ic/.yrsdPzhg/asmwRu3pQ.4OWaP.EG2CRK',1);
insert into users (username,password,enabled) values ('student','$2a$12$WYCJYzj6qV75ibzCdrL3IexcCd40JvaIPk6G0G/OzjoWZBIn6c.DO',1);

insert into authorities (username, authority) values ('admin','ROLE_ADMIN');
insert into authorities (username,authority) values ('admin','ROLE_USER');
insert into authorities (username,authority) values ('student','ROLE_USER');