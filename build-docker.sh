#!/usr/bin/bash

./gradlew test || exit_on_error "Тесты не пройдены";

FILE="src/main/resources/db/migration/V999__insert-data.sql"
python3 generate_data.py $FILE --migration;

# Если мы делаем миграцию с данными, то тесты не будут пройдены, т.к. id транспорта сдвинется, поэтому мы проходим тесты
# до сборки контейнера
./gradlew build -x test;
rm $FILE;

docker build -t transportbooking-docker .;
