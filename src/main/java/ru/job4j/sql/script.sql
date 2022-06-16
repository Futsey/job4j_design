create table electorate(
	id serial primary key,
	age smallint,
	name varchar (140),
	employed boolean,
	special_marks text
);

insert into electorate(age, name, employed, special_marks) values(32, 'Viktor', true, 'programmer');


ALTER TABLE electorate
RENAME COLUMN employed TO employeed;
update electorate set name = 'Vityok';

DELETE FROM electorate WHERE id = 2;