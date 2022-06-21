create table voter (
id serial primary key,
    name varchar(255),
	constituency smallint
);

insert into voter(name, constituency)
values
('George', 1),
('Tom', 1),
('Den', 1),
('Michael', 1),
('Jack', 1)
;

insert into voter(name, constituency)
values
('Gabrel', 2),
('Doloris', 2),
('Denis', 2),
('Deniska', 2),
('John', 2)
;

insert into voter(name)
values
('Petr'),
('Guram')
;

select v.name, v.constituency, c.name, c.political_party, c.constituency
from voter as v
inner join candidate as c
on v.id = c.id;

