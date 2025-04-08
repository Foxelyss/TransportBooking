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

print("BEGIN TRANSACTION;")

for i in range(500):
    city = randrange(1, 11)
    end_city = city

    while city == end_city:
        end_city = randrange(1, 11)

    company = randrange(1, 7)
    mean = randrange(1, 5)
    name = '"' + chr(randrange(ord("А"), ord("Я") + 1)) + "-" + str(randrange(1, 315)) + '"'
    places = randrange(1, 100)

    random_date_in_future = int(datetime.now().timestamp()) + randrange(86400, 5184000)
    random_date_in_future_next = int(datetime.now().timestamp()) + randrange(5200, 57600)

    print("insert into transportation values (null", name, random_date_in_future, random_date_in_future_next,
          city, end_city, mean, company, places, places, sep=", ", end=");\n")

print("COMMIT;")