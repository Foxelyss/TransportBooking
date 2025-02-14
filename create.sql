create table official (
    id INTEGER primary key,
    phone CHAR(32) not null,
    firstname CHAR(32) not null,
    surname CHAR(32) not null,
    lastname CHAR(32) not null,
    position CHAR(32) not null
);

create table company (
    id INTEGER primary key,
    name CHAR(128) not null,
    inn CHAR(128) not null,
    registration_address CHAR(128) not null,
    phone CHAR(128) not null,
    official INTEGER references official (id)
);


create table transportingmeans (
    id INTEGER primary key,
    name CHAR(128) not null
);

create table point (
    id INTEGER primary key,
    region CHAR(128) not null,
    city CHAR(128) not null
);


create table transportation (
    id INTEGER primary key,
    name CHAR(128) not null,
    departure TIMESTAMP not null,
    arrival TIMESTAMP not null,
    departurepoint INTEGER references point (id) not null,
    arrivalpoint INTEGER references point (id) not null,
    transportingmean INTEGER references transportingmeans (id) not null,
    company INTEGER references company (id),
    price UNSIGNED not null,
    placecount UNSIGNED not null,
    freeplacecount UNSIGNED not null
);

create table passanger (
    id INTEGER primary key,
    phone CHAR(32) not null,
    email CHAR(128) not null,
    firstname CHAR(32) not null,
    surname CHAR(32) not null,
    lastname CHAR(32),
    passport BIGINT not null
);

create table book (
    id INTEGER primary key,
    passanger INTEGER references passanger (id),
    place UNSIGNED not null,
    payment CHAR(128) not null,
    price FLOAT(2) not null,
    transportation INTEGER references transportation (id)
);