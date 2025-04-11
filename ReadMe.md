# Сервис пассажирских перевозок

## Бэкенд

Зависимости:
gradle, sqlite3

Для начала работы надо инициировать базу данных, для этого:
`sqlite3 databases/database.db ".read src/main/resources/schema.sql" ".read data.sql"`
(Можно пропустить этот шаг, но тогда тестовых данных **не будет**)

Затем запустить сервер

Команда старта сервера: `./gradlew :bootRun`

Код сервера находится здесь, в текущей папке.

Команда для теста:
`test.sh`

Для использования docker контейнера:

1. `build-docker.sh` - Для сборки
2. `start-docker.sh` - Старта

## Фронтенд

Код для мобильного приложения доступен по пути:
`travel_booking_app/`

**(Если папка пустая, сделайте `git submodule init`)**

Для запуска требуется фронтенда:
dart, flutter

Для старта запустить в папке: `flutter run`
Для компиляции для android: `flutter build apk`

Если у вас не показываются запросы, смените адрес сервера в файле `Config.dart`

```dart

final serverURI = 'адрес:8080';

```

### Работа с приложением

В приложении можно просматривать транспорт, выискивать ближайший к определённому времени, виду транспорта.

![Screenshot_20250411-191241](https://github.com/user-attachments/assets/33432e14-dd74-4e78-ae40-aaa05491d031)

Найдём транспорт и забронируем место

![Screenshot_20250411-205021](https://github.com/user-attachments/assets/791c1a6b-ee1d-4ffd-9f74-127ca1b3dd81)
![Screenshot_20250411-191250](https://github.com/user-attachments/assets/88289733-0d4f-43cd-aa62-1ac7d5563487)
![Screenshot_20250411-205643(2)](https://github.com/user-attachments/assets/1166e33b-969a-4d54-9db8-990e925376f3)


![Screenshot_20250411-191253](https://github.com/user-attachments/assets/f2050dbd-ac69-4ea0-ba1c-508e7a7e1976)
![Screenshot_20250411-191257](https://github.com/user-attachments/assets/8e29e6ae-80e2-4c64-ab15-adc4015f9ffc)

Чтобы просмотреть ваши билеты, воспользуйтесь вкладкой кабинет

![Screenshot_20250411-191302](https://github.com/user-attachments/assets/9d5b37d4-52b9-4f76-b23c-9e3c855cc289)
![Screenshot_20250411-191355](https://github.com/user-attachments/assets/8bec3eea-1b90-4441-89a8-87be0c19b1e4)
![Screenshot_20250411-191357](https://github.com/user-attachments/assets/2558052d-efd0-40e4-acae-e61150767d89)
