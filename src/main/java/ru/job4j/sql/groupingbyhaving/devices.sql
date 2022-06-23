create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

ALTER SEQUENCE devices_id_seq RESTART WITH 1;
UPDATE devices SET id = DEFAULT;

create table workers(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices (name, price)
 values
 ('Turning lathe', 15600),
 ('welding machine', 18600),
 ('screwdriver', 2000),
 ('wrench', 3400),
 ('Workbench', 4800)
;

insert into workers (name)
 values
 ('Alex'),
 ('Tom'),
 ('Den'),
 ('Ben'),
 ('Daniel')
;

insert into devices_people (device_id, people_id)
 values
 (1, 1),
 (2, 2),
 (3, 1),
 (3, 2),
 (3, 3),
 (3, 4),
 (3, 5),
 (4, 4),
 (4, 5),
 (5, 1),
 (5, 3),
 (5, 5)
;


select avg(price) from devices;
select min(price) from devices;
select max(price) from devices;

select dp.device_id, d.name, avg(d.price)
from devices_people as dp
join devices d
on dp.device_id = d.id
group by dp.device_id, d.name;

select dp.people_id, avg(d.price)
from devices_people as dp
join devices d
on dp.device_id = d.id
group by dp.people_id;

select dp.people_id, avg(d.price)
from devices_people as dp
join devices d
on dp.device_id = d.id
group by dp.people_id
having avg(d.price) > 5000;