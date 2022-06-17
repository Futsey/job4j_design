create table apartment_passport(
    id serial primary key,
    seria int,
	owner_id int references owner(id) unique
);

ALTER TABLE owner ADD COLUMN apartment_passport_id int references apartment_passport(id) unique;

insert into apartment_passport (seria, owner_id)
values
(101, 17),
(102, 18),
(103, 20),
(104, 19)
;

select * from apartment_passport