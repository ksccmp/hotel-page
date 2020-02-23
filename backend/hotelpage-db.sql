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
	num int primary key,
    size int not null,
    standard int not null,
    max int not null,
    price int not null,
    addprice int not null
);

create table reservation(
	id int auto_increment primary key,
	userid varchar(50) references user(userid),
    num int references room(num),
    startdate datetime not null,
    enddate datetime not null,
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
alter table logger convert to character set utf8;

insert into user(userid, password, nickname, position, gender, age, birth, phone, email, registerdate, updatedate) values ('abc', 'abc', 'abc', '브론즈', '남성', 50, now(), '010-3361-3633', 'naver', current_timestamp(), current_timestamp());
insert into room(num, size, standard, max, price, addprice) values (101, 18, 4, 6, 85000, 10000);
insert into reservation(userid, num, startdate, enddate, registerdate, updatedate) values ('abc', 101, '2020-01-01', '2020-01-02', current_timestamp(), current_timestamp());
insert into reservation(userid, num, startdate, enddate, registerdate, updatedate) values ('abc', 101, date_format(current_timeStamp(), '%Y-%m-%d %H:%i:%S'), '2020-01-06', current_timestamp(), current_timestamp());

select * from user;
select * from room;
select * from reservation;

drop table user;
drop table room;
drop table reservation;
drop table logger;