 create table owner(
     id serial primary key,
     house_id int references house(id),
     apartment_id int references apartment(id)
     name varchar (200)
 );

insert into owner(house_id, apartment_id, name)
values
(7, 29, 'Roman'),
(8, 30, 'Vladimir'),
(9, 31, 'Roman'),
(10, 32, 'Andrew'),
(11, 33, 'Nik');

select * from owner where name in ('Roman')
