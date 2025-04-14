#!/usr/bin/bash

./gradlew test || exit_on_error "Тесты не пройдены";

python3 generate_data.py;

# Если мы делаем миграцию с данными, то тесты не будут пройдены, т.к. id транспорта сдвинется, поэтому мы проходим тесты
# до сборки контейнера
./gradlew build -x test;
rm src/main/resources/db/migration/V999__insert-data.sql;

docker build -t transportbooking-docker .;
