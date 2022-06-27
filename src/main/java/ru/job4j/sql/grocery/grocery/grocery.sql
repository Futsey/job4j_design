create table product_type (
id serial primary key,
name varchar(255)
);

ALTER SEQUENCE product_type_id_seq RESTART WITH 1;
UPDATE product_type SET id = DEFAULT;

insert into product_type(name)
values
('Сыры'),
('Молоко'),
('Мороженое'),
('Кефир')
;

create table product (
id serial primary key,
name varchar(255),
fk_product_type_id int references product_type(id),
expired_date date,
price int
);

insert into product(name, fk_product_type_id, expired_date, price)
values
('Сыр Столичный', 1, '2022-07-08', 750),
('Сыр Голд', 1, '2022-09-05', 1032),
('Сыр Пошехонский', 1, '2022-10-22', 585),
('Сыр Российский', 1, '2022-09-10', 550),
('Сыр Моцарелла', 1, '2022-07-14', 1300),
('Сыр Белорусский', 1, '2022-07-21', 900),
('Молоко Село зеленое', 2, '2022-06-29', 85),
('Молоко Ишимское', 2, '2022-07-04', 82),
('Молоко Тюменское', 2, '2022-07-02', 81),
('Молоко Сургутское', 2, '2022-07-10', 94),
('Молоко Немолоко', 2, '2022-07-30', 121),
('Мороженое Alpen', 3, '2022-08-21', 49),
('Мороженое Сникерс', 3, '2022-09-17', 56),
('Мороженое Буренка', 3, '2022-07-13', 52),
('Мороженое Алтай', 3, '2022-07-28', 67),
('Мороженое Челябинск', 3, '2022-08-15', 38),
('Мороженое Тюмень', 3, '2022-08-25', 44),
('Кефир Ишимский', 4, '2022-07-01', 94),
('Кефир Губкинский', 4, '2022-07-04', 99),
('Кефир Тюменский', 4, '2022-07-02', 90),
('Кефир Просрочка', 4, '2022-05-02', 90)
;

select name
from product
where fk_product_type_id in (1);

SELECT p.name
FROM product  p
JOIN product_type pt
on p.fk_product_type_id = pt.id
where pt.name like '%Сыры%'
group by p.name;

select name
from product
where fk_product_type_id in (3);

SELECT p.name
FROM product  p
where p.name like '%Мороженое%'
group by p.name;

SELECT name, expired_date
FROM   product
WHERE  expired_date <= now()
ORDER  BY name;

SELECT * FROM product
WHERE price = (
SELECT MAX(price)
FROM product
);

SELECT pt.name, count(p.id) AS product_overall
FROM product  p
JOIN product_type pt
on p.fk_product_type_id = pt.id
group by pt.name;

SELECT p.name
     , count(p.fk_product_type_id) AS overall_count
FROM   product  p
WHERE  p.fk_product_type_id = 1
GROUP  BY p.name;

SELECT pt.name, count(p.id) AS product_overall
FROM product  p
JOIN product_type pt
on p.fk_product_type_id = pt.id
where fk_product_type_id in (1)
group by pt.name;

SELECT pt.name, count(p.id) AS product_overall
FROM product  p
JOIN product_type pt
on p.fk_product_type_id = pt.id
group by pt.name
order by pt.name;

SELECT pt.name, count(p.id) AS product_overall
FROM product  p
JOIN product_type pt
on p.fk_product_type_id = pt.id
where pt.name like '%Сыры%'
group by pt.name;

SELECT p.name
FROM product  p
JOIN product_type pt
on p.fk_product_type_id = pt.id
where fk_product_type_id in (1,2)
group by p.name;

SELECT p.name
FROM product  p
JOIN product_type pt
on p.fk_product_type_id = pt.id
where pt.name like '%Сыры%' or pt.name like '%Молоко%'
group by p.name;

SELECT pt.name, count(p.id) AS product_overall
FROM product  p
JOIN product_type pt
on p.fk_product_type_id = pt.id
group by pt.name
having count(p.id) < 10;

SELECT pt.name, p.name
FROM product  p
JOIN product_type pt
on p.fk_product_type_id = pt.id
group by pt.name, p.name
order by pt.name;
















