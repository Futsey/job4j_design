create table house (
	id serial primary key,
	houseNumber smallint
)

create table apartment (
	id serial primary key,
	apartmentNumber smallint,
	house_id smallint references house (id)
);

insert into house(houseNumber)
values
(20),
(21),
(22),
(23),
(24) ,
(25);

insert into apartment(apartmentNumber, house_id)
VALUES
(1, 7),
(2, 7),
(3, 7),
(1, 8),
(1, 9),
(1, 10),
(1, 11);

select * from house where id in (select id from apartment);
select * from house where id in (7);






