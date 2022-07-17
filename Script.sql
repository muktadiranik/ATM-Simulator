create database atm;

use atm;

drop table registration;

create table registration(
formNum varchar(20), 
name varchar(30), 
fathersName varchar(30), 
dob varchar(20), 
gender varchar(20), 
email varchar(20), 
maritalStatus varchar(20), 
address varchar(40), 
city varchar(20), 
zip varchar(20), 
division varchar(30), 
religion varchar(20), 
category varchar(30), 
income varchar(30), 
education varchar(20), 
occupation varchar(20), 
nid varchar(20), 
passport varchar(20), 
seniorCitizen varchar(40), 
existingAccount varchar(20), 
accountType varchar(20), 
cardNum integer unsigned not null auto_increment, 
pin varchar(20), 
services varchar(100), 
primary key(cardNum)
) auto_increment = 10000000;

create table account(cardNum int, date varchar(40), mode varchar(30), amount varchar(20));


select * from registration;

select cardNum from registration;

select * from account;











