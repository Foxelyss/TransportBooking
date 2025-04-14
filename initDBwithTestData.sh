#!/usr/bin/bash

./gradlew flywayMigrate;

python3 generate_data.py data.sql > /dev/null;
printf "\nЗаписываю тестовые данные в основной файл БД..."
sqlite3 databases/database.db ".read data.sql";
