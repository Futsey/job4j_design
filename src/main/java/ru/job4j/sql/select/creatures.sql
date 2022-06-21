create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date default CURRENT_DATE
);

insert into fauna(name, avg_age, discovery_date)
values
('Fish', 5, CURRENT_DATE - interval '4000 years'),
('Cat', 12, CURRENT_DATE - interval '3000 years'),
('Dog', 18, CURRENT_DATE - interval '3500 years'),
('Fly', 1, CURRENT_DATE - interval '2000 years'),
('Homo sapiens', 72, CURRENT_DATE - interval '2000 years'),
('Homo student', 1, CURRENT_DATE - interval '100 years'),
('Dinosaur', 158, null),
('Amphibians', 11000, CURRENT_DATE - interval '100 years')
;

select * from fauna where name LIKE '%Fish%';
select * from fauna where avg_age > 10000 and avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date > '01.09.1950';





