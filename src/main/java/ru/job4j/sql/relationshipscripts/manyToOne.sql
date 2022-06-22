create table house (
	id serial primary key,
	houseNumber smallint
)

create table apartment (
	id serial primary key,
	apartmentNumber smallint,
	fk_house_id smallint references house (id)
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
(1, 1),
(2, 1),
(3, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5);

select * from house where id in (select id from apartment);
select * from house where id in (7);






