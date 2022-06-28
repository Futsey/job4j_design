CREATE TYPE cartype AS ENUM ('Hatchback', 'Sedan', 'Wagon');

create table carcass (
id serial primary key,
current_cartype cartype
);

create table engine (
id serial primary key,
name varchar(255),
serial_number int,
eng_power float
);

create table transmission (
id serial primary key,
name varchar(255),
number_of_gears smallint
);

create table car (
id serial primary key,
name varchar(255),
fk_carcass_id int references carcass(id),
fk_engine_id int references engine(id),
fk_transmission_id int references transmission(id)
);

insert into carcass (current_cartype)
values
('Hatchback'),
('Hatchback'),
('Sedan'),
('Sedan'),
('Wagon'),
('Wagon');

insert into engine (name, serial_number, eng_power)
values
('2az', 3355, 2.4),
('2az', 3356, 2.4),
('2fe', 1258, 1.6),
('2ef', 1467, 3.0),
('Prototype engine', 1111, 6.5);

insert into transmission (name, number_of_gears)
values
('Race43', 6),
('Race44', 5),
('Race36', 4),
('Race38', 5),
('Race22', 3),
('Prototype transmission', 8);

insert into car (name, fk_carcass_id, fk_engine_id, fk_transmission_id)
values
('Mustang', 1, 4, 1),
('Oka', 3, 3, 5),
('Lada', 2, 1, 2),
('Mazda', 5, 2, 3),
('Toyota', 4, 5, 4),
('Kia', 6, null, null)
('Haval', null, null, 6);

select c.name, cc.current_cartype, e.name, t.name
from car as c
left join carcass cc
on c.fk_carcass_id = cc.id
left join engine e
on c.fk_engine_id = e.id
left join transmission t
on c.fk_transmission_id = t.id
group by c.name, cc.current_cartype, e.name, t.name;

select c.name, cc.current_cartype, e.name, t.name
from car as c
left join carcass cc
on c.fk_carcass_id = cc.id
left join engine e
on c.fk_engine_id = e.id
left join transmission t
on c.fk_transmission_id = t.id
where cc.current_cartype IS NULL or e.name IS NULL or t.name IS NULL
group by c.name, cc.current_cartype, e.name, t.name;

select c.name, cc.current_cartype
from car as c
left join carcass cc
on c.fk_carcass_id = cc.id
where cc.current_cartype is NULL
group by c.name, cc.current_cartype;

select c.name,e.name
from car as c
left join engine e
on c.fk_engine_id = e.id
where e.name IS NULL
group by c.name, e.name;

select c.name, t.name
from car as c
left join transmission t
on c.fk_transmission_id = t.id
where t.name IS NULL
group by c.name, t.name;




