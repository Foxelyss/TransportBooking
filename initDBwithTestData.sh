#!/usr/bin/bash

./gradlew flywayMigrate;

echo no | python3 generate_data.py > /dev/null;
printf "\nЗаписываю тестовые данные в основной файл БД..."
sqlite3 databases/database.db ".read data.sql";
