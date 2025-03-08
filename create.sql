create table Company (
    id INTEGER primary key,
    name CHAR(128) not null,
    inn CHAR(128) not null,
    registration_address CHAR(128) not null,
    phone CHAR(128) not null,
    token_date date,
    token char(255)
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
    price UNSIGNED not null,
    place_count UNSIGNED not null,
    free_place_count UNSIGNED not null
);
create table Passenger (
    passport BIGINT primary key,
    phone bigint not null,
    email CHAR(128) not null,
    firstname CHAR(32) not null,
    surname CHAR(32) not null,
    lastname CHAR(32)
);
create table book (
    id INTEGER primary key,
    passenger bigint references passenger (passport),
    payment CHAR(128) not null,
    price FLOAT(2) not null,
    transportation INTEGER references transportation (id)
);