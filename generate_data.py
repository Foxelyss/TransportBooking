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


import argparse
from datetime import datetime
from random import randrange

parser = argparse.ArgumentParser("Генератор тестовых данных")
parser.add_argument("filepath", help="Путь к файлу", type=str)
parser.add_argument("migration", help="Миграция ли?", type=str)
args = parser.parse_args()

filename = args.filepath
migration = args.migration.lower() == "true"

with open(filename, "w") as file:
    print("insert into point values (null,'г. Томск','Томская область','Томск');",file=file)
    print("insert into point values (null,'г. Новосибирск','Новосибирская область','Новосибирск') ;",file=file)
    print("insert into point values (null,'г. Казань','Казанская область','Казань') ;",file=file)
    print("insert into point values (null,'г. Магадан','Магаданская область','Новосибирск') ;",file=file)
    print("insert into point values (null,'г. Самара','Самарская область','Самара') ;",file=file)
    print("insert into point values (null,'г. Москва','Москва','Москва') ;",file=file)
    print("insert into point values (null,'г. Санкт-Петербург','Санкт-Петербург','Санкт-Петербург') ;",file=file)
    print("insert into point values (null,'г. Горно-Алтайск','Республика Алтай','Горно-Алтайск');",file=file)
    print("insert into point values (null,'г. Нижний Новгород','Нижегородская область','Нижний Новгород');",file=file)
    print("insert into point values (null,'г. Севастополь', 'Севастополь', 'Севастополь');",file=file)
    print("",file=file)
    print("insert into Company values (null,'ООО Лана','123123','г. Томск', 'Домашний номер: 12-34-56 Обычный: 8 963 312 75 66');",file=file)
    print("insert into Company values (null,'ООО РЖД', '12312389', 'г. Москва', 'Домашний номер: 12-31-56 Обычный: 8 123 312 75 66');",file=file)
    print("insert into Company values (null,'ИП Сидоров','5645645623','г. Томск', 'Домашний номер: 12-56-56 Обычный: 8 963 312 75 66');",file=file)
    print("insert into Company values (null,'ИП Петров','121233123','г. Новгород', 'Домашний номер: 12-14-56 Обычный: 8 963 312 75 66');",file=file)
    print("insert into Company values (null,'ИП Добраков','1238678123','г. Томск', 'Домашний номер: 17-34-56 Обычный: 8 963 312 75 66');",file=file)
    print("insert into Company values (null,'ИП Сергеев','123126783','г. Новосибирск', 'Домашний номер: 10-34-56 Обычный: 8 963 122 86 66');",file=file)
    print("",file=file)
    print("insert into TransportingMeans values(null,'Ж/Д');",file=file)
    print("insert into TransportingMeans values(null,'Автобусом');",file=file)
    print("insert into TransportingMeans values(null,'Самолётом');",file=file)
    print("insert into TransportingMeans values(null,'Ж/Д+Автобус');",file=file)
    print("insert into TransportingMeans values(null,'Ж/Д+Самолёт');",file=file)

    if not migration:
        print("BEGIN TRANSACTION;",file=file)

    for i in range(500):
        city = randrange(1, 11)
        end_city = city

        while city == end_city:
            end_city = randrange(1, 11)

        company = randrange(1, 7)
        mean = randrange(1, 5)
        name = "'" + chr(randrange(ord("А"), ord("Я") + 1)) + \
            "-" + str(randrange(1, 315)) + "'"
        places = randrange(1, 100)

        random_date_in_future = int(
            datetime.now().timestamp()) + randrange(86400, 5184000)
        random_date_in_future_next = random_date_in_future + randrange(5200, 57600)

        price = randrange(10000, 6500000) / 100

        print("insert into transportation values (null", name, random_date_in_future, random_date_in_future_next,
            city, end_city, mean, company, price, places, places, sep=", ", end=");\n",file=file)
    
    if not migration:
        print("COMMIT;",file=file)
