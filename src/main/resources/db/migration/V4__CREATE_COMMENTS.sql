create table comments (
	id bigserial primary key,
	author varchar(255) not null,
	comment text not null,
	post_id integer,

	foreign key (post_id) references posts(id));