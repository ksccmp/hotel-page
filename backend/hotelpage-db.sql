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
insert into reservation(userid, num, startdate, enddate, registerdate, updatedate) values ('abc', 101, '2020-01-01', '2020-01-02', current_timestamp(), current_timestamp());
insert into reservation(userid, num, startdate, enddate, registerdate, updatedate) values ('abc', 101, date_format(current_timeStamp(), '%Y-%m-%d %H:%i:%S'), '2020-01-06', current_timestamp(), current_timestamp());

insert into room(id, num, name, contents, addcontents, size, standard, max, rentprice, rentaddprice, fullprice, fulladdprice) values (1, 101, "스탠다드 룸", "아늑한 분위기의 스탠다드 룸", null, 10, 4, 6, 45000, 5000, 110000, 10000);
insert into room(id, num, name, contents, addcontents, size, standard, max, rentprice, rentaddprice, fullprice, fulladdprice) values (2, 102, "스탠다드 룸", "아늑한 분위기의 스탠다드 룸", null, 10, 4, 6, 45000, 5000, 110000, 10000);
insert into room(id, num, name, contents, addcontents, size, standard, max, rentprice, rentaddprice, fullprice, fulladdprice) values (3, 103, "스위트 룸", "아늑하고 넓은 분위기의 스위트 룸", null, 15, 6, 8, 60000, 8000, 160000, 15000);
insert into room(id, num, name, contents, addcontents, size, standard, max, rentprice, rentaddprice, fullprice, fulladdprice) values (4, 201, "스탠다드 룸", "아늑한 분위기의 스탠다드 룸", null, 10, 4, 6, 45000, 5000, 110000, 10000);
insert into room(id, num, name, contents, addcontents, size, standard, max, rentprice, rentaddprice, fullprice, fulladdprice) values (5, 202, "스탠다드 룸", "아늑한 분위기의 스탠다드 룸", null, 10, 4, 6, 45000, 5000, 110000, 10000);
insert into room(id, num, name, contents, addcontents, size, standard, max, rentprice, rentaddprice, fullprice, fulladdprice) values (6, 203, "스위트 룸", "아늑하고 넓은 분위기의 스위트 룸", null, 15, 6, 8, 60000, 8000, 160000, 15000);

select * from user;
select * from room;
select * from reservation;
select * from logger;

delete from user;
delete from room;

drop table user;
drop table room;
drop table reservation;
drop table logger;