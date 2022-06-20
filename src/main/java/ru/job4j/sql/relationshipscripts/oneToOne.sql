create table apartment_passport(
    id serial primary key,
    seria int,
	owner_id int references owner(id) unique
);

insert into apartment_passport (seria, owner_id)
values
(101, 1),
(102, 2),
(103, 3),
(104, 4),
(105, 4)
;

select * from apartment_passport