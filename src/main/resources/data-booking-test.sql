insert into point values (null,'г. Томск','Томская область','Томск');
insert into point values (null,'г. Новосибирск','Новосибирская область','Новосибирск') ;
insert into point values (null,'г. Санкт-Петербург','Санкт-Петербург','Санкт-Петербург') ;
insert into point values (null,'г. Севастополь', 'Севастополь', 'Севастополь');

insert into Company values (null,'ООО Лана','123123','г. Томск', 'Домашний номер: 12-34-56 Обычный: 8 963 312 75 66');
insert into Company values (null,'ООО РЖД', '12312389', 'г. Москва', 'Домашний номер: 12-31-56 Обычный: 8 123 312 75 66');

insert into TransportingMeans values(null,'Ж/Д');
insert into TransportingMeans values(null,'Автобусом');

insert into transportation values (null, 'Будущее', unixepoch()+86400, unixepoch()+86400+86400, 1, 2, 1, 1, 26354, 50, 50);
insert into transportation values (null, 'Прошлое', unixepoch()-86400-86400, unixepoch()-86400, 1, 2, 1, 1, 26354, 50, 50);
insert into transportation values (null, '123', unixepoch()+86400, unixepoch()+86400+86400, 1, 2, 1, 1, 26354, 2, 0);

insert into passenger values (2, 123, 321,"unknown@example.com","Максим","Сидоров","");
insert into book values (null,2,"Картой",123,2);