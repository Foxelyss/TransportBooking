# create table Transportation (
#     id INTEGER primary key,
#     name CHAR(128) not null,
#     departure TIMESTAMP not null,
#     arrival TIMESTAMP not null,
#     departure_point INTEGER references point (id) not null,
#     arrival_point INTEGER references point (id) not null,
#     transporting_mean INTEGER references transportingmeans (id) not null,
#     company INTEGER references company (id),
#     price UNSIGNED not null,
#     place_count UNSIGNED not null,
#     free_place_count UNSIGNED not null check(
#         free_place_count between 0 and place_count
#     )
# );


from datetime import datetime
from random import randrange

print("insert into point values (null,'г. Томск','Томская область','Томск');")
print("insert into point values (null,'г. Новосибирск','Новосибирская область','Новосибирск') ;")
print("insert into point values (null,'г. Казань','Казанская область','Казань') ;")
print("insert into point values (null,'г. Магадан','Магаданская область','Новосибирск') ;")
print("insert into point values (null,'г. Самара','Самарская область','Самара') ;")
print("insert into point values (null,'г. Москва','Москва','Москва') ;")
print("insert into point values (null,'г. Санкт-Петербург','Санкт-Петербург','Санкт-Петербург') ;")
print("insert into point values (null,'г. Горно-Алтайск','Республика Алтай','Горно-Алтайск');")
print("insert into point values (null,'г. Нижний Новгород','Нижегородская область','Нижний Новгород');")
print("insert into point values (null,'г. Севастополь', 'Севастополь', 'Севастополь');")
print("")
print("insert into Company values (null,'ООО Лана','123123','г. Томск', 'Домашний номер: 12-34-56 Обычный: 8 963 312 75 66');")
print("insert into Company values (null,'ООО РЖД', '12312389', 'г. Москва', 'Домашний номер: 12-31-56 Обычный: 8 123 312 75 66');")
print("insert into Company values (null,'ИП Сидоров','5645645623','г. Томск', 'Домашний номер: 12-56-56 Обычный: 8 963 312 75 66');")
print("insert into Company values (null,'ИП Петров','121233123','г. Новгород', 'Домашний номер: 12-14-56 Обычный: 8 963 312 75 66');")
print("insert into Company values (null,'ИП Добраков','1238678123','г. Томск', 'Домашний номер: 17-34-56 Обычный: 8 963 312 75 66');")
print("insert into Company values (null,'ИП Сергеев','123126783','г. Новосибирск', 'Домашний номер: 10-34-56 Обычный: 8 963 122 86 66');")
print("")
print("insert into TransportingMeans values(null,'Ж/Д');")
print("insert into TransportingMeans values(null,'Автобусом');")
print("insert into TransportingMeans values(null,'Самолётом');")
print("insert into TransportingMeans values(null,'Ж/Д+Автобус');")
print("insert into TransportingMeans values(null,'Ж/Д+Самолёт');")


print("BEGIN TRANSACTION;")

for i in range(500):
    city = randrange(1, 11)
    end_city = city

    while city == end_city:
        end_city = randrange(1, 11)

    company = randrange(1, 7)
    mean = randrange(1, 5)
    name = "'" + chr(randrange(ord("А"), ord("Я") + 1)) + "-" + str(randrange(1, 315)) + "'"
    places = randrange(1, 100)

    random_date_in_future = int(datetime.now().timestamp()) + randrange(86400, 5184000)
    random_date_in_future_next = int(datetime.now().timestamp()) + randrange(5200, 57600)

    price = randrange(10000,6500000) / 100

    print("insert into transportation values (null", name, random_date_in_future, random_date_in_future_next,
          city, end_city, mean, company, price, places, places, sep=", ", end=");\n")

print("COMMIT;")