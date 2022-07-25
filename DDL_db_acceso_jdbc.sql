CREATE TABLE tbl_autor(
	autor_id serial primary key,
	nombre varchar(45),
	email varchar(45)
);

CREATE TABLE tbl_libro (
	libro_id serial primary key,
	titulo varchar(128),
	description varchar(512),
	publicado varchar(100),
	autor_id int,
	precio decimal,
	foreign key (autor_id) 
	references tbl_autor (autor_id)
);
