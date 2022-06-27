create table departments (
id serial primary key,
name varchar(255)
);

ALTER SEQUENCE departments_id_seq RESTART WITH 1;
UPDATE departments SET id = DEFAULT;

create table employees (
id serial primary key,
name varchar(255),
fk_departments_id int references departments(id)
);

insert into departments(name)
values
('Внутренней политики'),
('Строительства и архитектуры'),
('Систем жизнеобеспечения'),
('Информационных технологий'),
('По делам малочисленных народов'),
('Экономики'),
('Бездельников')
;

insert into employees(name, fk_departments_id)
values
('Семен', 5),
('Вадим', 2),
('Артём', 3),
('Андрей', 5),
('Антон', 4),
('Влад', 1),
('Пётр', 1),
('Стас', 2),
('Лана', 6),
('Георгий', 3),
('Михаил', 6),
('Марина', 2),
('Ольга', 3),
('Ирина', 6),
('Олег', null)
;

select * from employees e
left join departments d
on d.id = e.fk_departments_id;

select * from departments d
right join employees e
on e.fk_departments_id = d.id;

select * from departments d
left join employees e
on e.fk_departments_id = d.id;

select * from employees e
right join departments d
on d.id = e.fk_departments_id;

select * from employees e
full join departments d
on d.id = e.fk_departments_id;

select * from employees e
cross join departments d;

select d.name
from departments d
left join employees e
on e.fk_departments_id = d.id
where e.name IS NULL;

create table teens (
id serial primary key,
name varchar(255),
gender boolean -- true М false Ж
);

insert into teens(name, gender)
values
('Семен', true),
('Вадим', true),
('Артём', true),
('Андрей', true),
('Антон', true),
('Влад', true),
('Пётр', true),
('Стас', true),
('Лана', false),
('Георгий', true),
('Михаил', true),
('Марина', false),
('Ольга', false),
('Ирина', false),
('Олег', true)
;

create table pairs (
id serial primary key,
fk_teens_id int references teens(id)
);

SELECT * FROM teens;

SELECT m.name as leftColumn, w.name as rightColumn
FROM teens m CROSS JOIN teens w
WHERE m.gender != w.gender;





