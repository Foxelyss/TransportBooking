create table Company (
    id INTEGER primary key,
    name CHAR(128) not null,
    inn CHAR(128) not null,
    registration_address CHAR(128) not null,
    phone CHAR(128) not null
);
create table TransportingMeans (
    id INTEGER primary key,
    name CHAR(128) not null
);
create table Point (
    id INTEGER primary key,
    name CHAR(128) not null,
    region CHAR(128) not null,
    city CHAR(128) not null
);
create table Transportation (
    id INTEGER primary key,
    name CHAR(128) not null,
    departure TIMESTAMP not null,
    arrival TIMESTAMP not null,
    departure_point INTEGER references point (id) not null,
    arrival_point INTEGER references point (id) not null,
    transporting_mean INTEGER references transportingmeans (id) not null,
    company INTEGER references company (id),
    price float(2) not null,
    place_count UNSIGNED not null,
    free_place_count UNSIGNED not null check(
        free_place_count between 0 and place_count
    )
);
create table Passenger (
    id INTEGER primary key,
    passport BIGINT not null,
    phone bigint not null,
    email CHAR(128) not null,
    firstname CHAR(32) not null CHECK(firstname != ''),
    surname CHAR(32) not null CHECK(surname != ''),
    lastname CHAR(32)
);
create table book (
    id INTEGER primary key,
    passenger bigint references passenger (id),
    payment CHAR(128) not null,
    price FLOAT(2) not null,
    transportation INTEGER references transportation (id)
);