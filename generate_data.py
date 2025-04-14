#!/bin/env python3

import argparse
from datetime import datetime
from random import randrange

parser = argparse.ArgumentParser("Генератор тестовых данных")
parser.add_argument("filepath", help="Путь к файлу", type=str)
parser.add_argument('--migration', dest='migration',
                    action='store_true', help='Миграционный SQL файл', default=False)
args = parser.parse_args()

filename = args.filepath
migration = args.migration

with open(filename, "w") as file:
    file.write("/* Тестовые данные для наполнения БД*/")
    file.write("\ninsert into point values (null,'г. Томск','Томская область','Томск');")
    file.write("\ninsert into point values (null,'г. Новосибирск','Новосибирская область','Новосибирск') ;")
    file.write("\ninsert into point values (null,'г. Казань','Казанская область','Казань') ;")
    file.write("\ninsert into point values (null,'г. Магадан','Магаданская область','Новосибирск') ;")
    file.write("\ninsert into point values (null,'г. Самара','Самарская область','Самара') ;")
    file.write("\ninsert into point values (null,'г. Москва','Москва','Москва') ;")
    file.write("\ninsert into point values (null,'г. Санкт-Петербург','Санкт-Петербург','Санкт-Петербург') ;")
    file.write("\ninsert into point values (null,'г. Горно-Алтайск','Республика Алтай','Горно-Алтайск');")
    file.write("\ninsert into point values (null,'г. Нижний Новгород','Нижегородская область','Нижний Новгород');")
    file.write("\ninsert into point values (null,'г. Севастополь', 'Севастополь', 'Севастополь');")
    file.write("\n")
    file.write("\ninsert into Company values (null,'ООО Лана','123123','г. Томск', 'Домашний номер: 12-34-56 Обычный: 8 963 312 75 66');")
    file.write("\ninsert into Company values (null,'ООО РЖД', '12312389', 'г. Москва', 'Домашний номер: 12-31-56 Обычный: 8 123 312 75 66');")
    file.write("\ninsert into Company values (null,'ИП Сидоров','5645645623','г. Томск', 'Домашний номер: 12-56-56 Обычный: 8 963 312 75 66');")
    file.write("\ninsert into Company values (null,'ИП Петров','121233123','г. Новгород', 'Домашний номер: 12-14-56 Обычный: 8 963 312 75 66');")
    file.write("\ninsert into Company values (null,'ИП Добраков','1238678123','г. Томск', 'Домашний номер: 17-34-56 Обычный: 8 963 312 75 66');")
    file.write("\ninsert into Company values (null,'ИП Сергеев','123126783','г. Новосибирск', 'Домашний номер: 10-34-56 Обычный: 8 963 122 86 66');")
    file.write("\n")
    file.write("\ninsert into TransportingMeans values(null,'Ж/Д');")
    file.write("\ninsert into TransportingMeans values(null,'Автобусом');")
    file.write("\ninsert into TransportingMeans values(null,'Самолётом');")
    file.write("\ninsert into TransportingMeans values(null,'Ж/Д+Автобус');")
    file.write("\ninsert into TransportingMeans values(null,'Ж/Д+Самолёт');")

    if not migration:
        file.write("BEGIN TRANSACTION;")

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
        random_date_in_future_next = random_date_in_future + \
            randrange(5200, 57600)

        price = randrange(10000, 6500000) / 100

        print("insert into transportation values (null", name, random_date_in_future, random_date_in_future_next,
              city, end_city, mean, company, price, places, places, sep=", ", end=");\n", file=file)

    if not migration:
        file.write("COMMIT;")
