create table users (
	id bigserial primary key,
	username varchar(255) not null unique,
	email varchar(255) not null unique,
	password varchar(255) not null,
	role varchar(255) not null default 'ROLE_USER');

insert into users (username, email, password, role) 
	values (
		'User', 
		'user@gmail.com', 
		'$2a$12$6/VI72YWnKJImm9PtPl0peQAAHNFJ89luPBfC6QvMWN5fVvZwZl8m', 
		'ROLE_USER'),
	
		('Admin',
		'admin@gmail.com',
		'$2a$12$Tkjibt4irhU2jSsxpAVwh.taXf7rLk0bFy6Oa/pNkTbBVqQeYwgJK',
		'ROLE_ADMIN');