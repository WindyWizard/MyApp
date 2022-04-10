create table posts (
	id bigserial primary key,
	author varchar(255) not null,
	title varchar(255) not null unique,
	content text not null,
	image varchar(255));