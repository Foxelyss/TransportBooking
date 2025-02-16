create table Official (
    id INTEGER primary key,
    phone CHAR(32) not null,
    firstname CHAR(32) not null,
    surname CHAR(32) not null,
    lastname CHAR(32) not null,
    position CHAR(32) not null
);

create table Company (
    id INTEGER primary key,
    name CHAR(128) not null,
    inn CHAR(128) not null,
    registration_address CHAR(128) not null,
    phone CHAR(128) not null,
    official INTEGER references official (id)
);


create table TransportingMeans (
    id INTEGER primary key,
    name CHAR(128) not null
);

create table Point (
    id INTEGER primary key,
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
    price UNSIGNED not null,
    place_count UNSIGNED not null,
    free_place_count UNSIGNED not null
);

create table Passenger (
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
    passenger INTEGER references passenger (id),
    place UNSIGNED not null,
    payment CHAR(128) not null,
    price FLOAT(2) not null,
    transportation INTEGER references transportation (id)
);