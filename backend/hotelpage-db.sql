create database hotelpage;
use hotelpage;

create table user(
	id int auto_increment primary key,
	userid varchar(50) not null,
    password varchar(50) not null,
    nickname varchar(50) not null,
    position enum('브론즈', '실버', '골드', '플래티넘', '다이아', '1급', '2급', '3급', '4급', '5급') not null,
    gender enum('남성', '여성') not null,
    age int not null,
    birth datetime not null,
    phone varchar(50) not null,
    email varchar(100) not null,
    registerdate datetime not null,
    updatedate datetime not null
);

create table room(
	id int primary key,
	num int not null,
    name varchar(50) not null,
    contents varchar(500) not null,
    addcontents varchar(500),
    size int not null,
    standard int not null,
    max int not null,
    rentprice int not null,
    rentaddprice int not null,
    fullprice int not null,
    fulladdprice int not null
);

create table reservation(
	id int auto_increment primary key,
	uid varchar(50) references user(id),
    rid int references room(id),
    roomname varchar(50) not null,
    roomnum varchar(50) not null,
    name varchar(50) not null,
    price int not null,
    phone varchar(50) not null,
    form enum ('대실', '숙박') not null,
    way enum ('도보', '차량') not null,
    person int not null,
    startdate datetime not null,
    enddate datetime not null,
    registerdate datetime not null,
    updatedate datetime not null
);

create table request (
	id int auto_increment primary key,
    type varchar(50) not null,
    title varchar(100) not null,
    contents varchar(500) not null,
    nickname varchar(50) not null,
    phone varchar(50) not null,
    email varchar(100) not null,
    registerdate datetime not null,
    updatedate datetime not null
);

create table logger(
	id int auto_increment primary key,
    tablename varchar(50) not null,
    action varchar(50) not null,
    registerdate datetime not null
);

set charset utf8;

alter database hotelpage default character set utf8;
alter table user convert to character set utf8;
alter table room convert to character set utf8;
alter table reservation convert to character set utf8;
alter table request convert to character set utf8;
alter table logger convert to character set utf8;

insert into room(id, num, name, contents, addcontents, size, standard, max, rentprice, rentaddprice, fullprice, fulladdprice) values (1, 101, "스탠다드 룸", "아늑한 분위기의 스탠다드 룸", null, 10, 4, 6, 45000, 5000, 110000, 10000);
insert into room(id, num, name, contents, addcontents, size, standard, max, rentprice, rentaddprice, fullprice, fulladdprice) values (2, 102, "스탠다드 룸", "아늑한 분위기의 스탠다드 룸", null, 10, 4, 6, 45000, 5000, 110000, 10000);
insert into room(id, num, name, contents, addcontents, size, standard, max, rentprice, rentaddprice, fullprice, fulladdprice) values (3, 103, "스위트 룸", "아늑하고 넓은 분위기의 스위트 룸", null, 15, 6, 8, 60000, 8000, 160000, 15000);
insert into room(id, num, name, contents, addcontents, size, standard, max, rentprice, rentaddprice, fullprice, fulladdprice) values (4, 201, "스탠다드 룸", "아늑한 분위기의 스탠다드 룸", null, 10, 4, 6, 45000, 5000, 110000, 10000);
insert into room(id, num, name, contents, addcontents, size, standard, max, rentprice, rentaddprice, fullprice, fulladdprice) values (5, 202, "스탠다드 룸", "아늑한 분위기의 스탠다드 룸", null, 10, 4, 6, 45000, 5000, 110000, 10000);
insert into room(id, num, name, contents, addcontents, size, standard, max, rentprice, rentaddprice, fullprice, fulladdprice) values (6, 203, "스위트 룸", "아늑하고 넓은 분위기의 스위트 룸", null, 15, 6, 8, 60000, 8000, 160000, 15000);

select * from reservation where uid = 2;

select * from user;
select * from room;
select * from reservation;
select * from request;
select * from logger;

select date_format(current_timestamp(), '%Y-%m-%d');
select date_add(current_timestamp(), interval 1 year);

select * from room where id = 1 and id not in (
select rid from reservation where '2020-02-27' between startdate and DATE_ADD(enddate, INTERVAL -1 DAY) or '2020-02-28' between DATE_ADD(startdate, INTERVAL 1 DAY) and enddate);
delete from user;
delete from room;

drop table user;
drop table room;
drop table reservation;
drop table request;
drop table logger;