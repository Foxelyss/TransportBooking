# Сервис пассажирских перевозок

## Бэкенд

Для запуска требуется бэкенда:
gradle, sqlite3

Для начала работы надо инициировать базу данных, для этого:
`sqlite3 mydatabase.db ".read create.sql" ".read data.sql"`

Затем запустить сервер

Команда старта сервера: `gradlew :bootRun`

Код сервера находится здесь, в текущей папке.

## Фронтенд

Код для мобильного приложения доступен по пути:
`travel_booking_app/`

Для запуска требуется фронтенда:
dart, flutter

Для старта запустить в папке: `flutter run`
Для компиляции: `flutter build apk`

Если у вас не показываются запросы, смените адрес сервера в файле `Config.dart`
``dart
final serverURI = 'адрес:8080';
``

### Работа с приложением

В приложении можно просматривать транспорт, выискивать ближайший к определённому времени, виду транспорта.
![image](https://github.com/user-attachments/assets/9cb85835-56a4-404c-a6e6-e74e9feb3598)

Найдём транспорт и забронируем место

![image](https://github.com/user-attachments/assets/c0178c79-6da9-463f-a88d-5c74b86eecb3)
![image](https://github.com/user-attachments/assets/811ae1d1-6737-4a56-a235-9055cc28acbe)

![image](https://github.com/user-attachments/assets/52a91171-0c1d-4b6c-b819-b115c6ef2a57)

Чтобы просмотреть ваши билеты, воспользуйтесь вкладкой кабинет

![image](https://github.com/user-attachments/assets/21f82cab-0415-45ee-b488-2b60209a9a20)
![image](https://github.com/user-attachments/assets/1ccb0a2d-7dab-490a-b138-82f668f217f4)
