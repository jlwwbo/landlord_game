drop database if exists landlord;
create database landlord default character set utf8mb4;
use landlord;

create table if not exists login (
username varchar(50) not null,
pwd varchar(50) not null,
userid int not null,
primary key (username,pwd)
);


