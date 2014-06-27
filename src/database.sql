drop table spitter;

delete from spitter where id=103;

create table spitter (
	id number,
	userFullName varchar2(30),
	username varchar2(30),
	password varchar2(30),
	email varchar2(30),
	profile_image varchar2(30),
	constraint spitter_pk primary key (id),
	constraint spitter_username_uk unique (username)
);