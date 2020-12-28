drop schema if exists registration cascade;
create schema registration;
commit;

create table registration.users (
    id serial not null primary key,
    login varchar(50) not null,
    password varchar(20) not null);

create table registration.users_info(
    id serial not null primary key references registration.users (id),
    name varchar(50) not null,
    secondName varchar(50) not null,
    age smallint,
    location varchar(50) not null
    CONSTRAINT usersAge CHECK (age >= 0 and age <=120)
);

insert into registration.users (login, password)
values
('adminLogin', 'adminPas');

insert into registration.users_info(name, secondName, location, age)
values
('Guster', 'Igonin', 'Phuket', 32);

insert into registration.users (login, password)
values
('adminLogin2', 'adminPas2');

insert into registration.users_info(name, secondName, location, age)
values
('Gustera', 'Igoniadfn', 'Phuketasdf', 32);