 create table owner(
     id serial primary key,
     name varchar (200)
 );

insert into owner(house_id, apartment_id, name)
values
('Roman'),
('Vladimir'),
('Igor'),
('Andrew'),
('Nik');

 create table owning(
     id serial primary key,
     fk_owner_id int references owner(id),
     fk_apartment_id int references apartment(id)
 );

insert into owning(owner_id, apartment_id, name)
values
(1, 1, 'First'),
(1, 2, 'Second'),
(2, 3, 'Third'),
(3, 4, 'Fourth'),
(4, 5, 'Fifth');

select * from owner where name in ('Roman')
