create table USER
(
    ID      int auto_increment
        primary key,
    Name    varchar(120) not null,
    Email   varchar(120) not null,
    Country varchar(120) null
);

