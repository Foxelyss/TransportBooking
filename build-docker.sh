#!/usr/bin/bash

./gradlew test || exit_on_error "Тесты не пройдены";

FILE="src/main/resources/db/migration/V999__insert-data.sql"

read -n 1 -p "Хотите тестовые данные? (Y/n)" testing_data_generation
[ -z "$testing_data_generation" ] && testing_data_generation="y"

if [[ "$testing_data_generation" == [Yy] ]]; then
    printf "\nПроизвожу генерацию данных\n"
    python3 generate_data.py $FILE --migration;
else
    printf "\nСборка без генерации данных"
fi

# Если мы делаем миграцию с данными, то тесты не будут пройдены, т.к. id транспорта сдвинется, поэтому мы проходим тесты
# до сборки контейнера
./gradlew build -x test;
rm $FILE;

docker build -t transportbooking-docker .;
