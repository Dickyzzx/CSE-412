drop table if exists  "User";
drop table if exists  "pets";
drop table if exists  "product";
drop table if exists  "seller_supp";
drop table if exists  "customer_order";
CREATE TABLE "pets" (
	id varchar(32) not null,
	name varchar(255) not null,
	age int not null,
	location varchar(255) null,
	species varchar(255) null,
	breed varchar(255) null,
	price double precision not null,
	seller_uid varchar(255) not null,
	buy_uid varchar(255) null,
	status varchar(10) not null,
	primary key (id)
);

CREATE TABLE "product" (
	id varchar(32) not null,
	description varchar(255) not null,
	brand varchar(255) not null,
	price double precision not null,
	seller_uid varchar(255) not null,
	buy_uid varchar(255) null,
	status varchar(10) not null,
	primary key (id)
);

create table seller_supp (
    uid varchar(255) not null,
	pid varchar(32) not null,
	identifier int not null,
	price double precision not null
);

create table customer_order (
    uid varchar(255) not null,
	pid varchar(32) not null,
	identifier int not null,
	price double precision not null
);

create table "User" (
	uid varchar(255) not null,
	phone varchar(255) null,
	password varchar(255) not null,
	name varchar(255) not null,
	email varchar(255) null,
	zip_code varchar(255) null,
	primary key (uid)
);