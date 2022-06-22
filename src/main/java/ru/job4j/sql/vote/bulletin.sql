
create table candidate (
id serial primary key,
    name varchar(255),
	political_party text,
	constituency smallint
);

create table voter (
id serial primary key,
    name varchar(255),
	constituency smallint
);

create table bulletin (
id serial primary key,
    number int unique,
	candidate_id int references candidate(id),
	voter_id int references voter(id)
);

insert into candidate(name, political_party, constituency)
values
('mr. Bin', 'DriDro', 1),
('mr. Pu', 'DriDro', 1),
('mr. Me', 'DriDro', 1),
('mr. Vo', 'DriDro', 1)
;

insert into candidate(name, political_party, constituency)
values
('mr. Baiden', 'BroVro', 2),
('mr. Zel', 'BroVro', 2),
('mr. Fon', 'BroVro', 2),
('mr. Sho', 'BroVro', 2)
;

insert into candidate(name, political_party)
values
('mr. Superman', 'HimSelf')
;

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

insert into bulletin(number, candidate_id, voter_id) values (1, 1, 1);
insert into bulletin(number, candidate_id, voter_id) values (2, 2, 2);
insert into bulletin(number, candidate_id, voter_id) values (3, 3, 3);
insert into bulletin(number, candidate_id, voter_id) values (4, 4, 4);
insert into bulletin(number, candidate_id, voter_id) values (5, 5, 5);
insert into bulletin(number, candidate_id, voter_id) values (6, 6, 6);
insert into bulletin(number, candidate_id, voter_id) values (7, 6, 7);
insert into bulletin(number, candidate_id, voter_id) values (8, 1, 9);
insert into bulletin(number, candidate_id, voter_id) values (9, 1, 9);

select v.name, v.constituency, c.name, c.political_party, c.constituency
from voter as v
inner join candidate as c
on v.id = c.id;

select v.name, v.constituency
from voter as v
where v.constituency is null;

select v.name, c.name, c.political_party
from voter as v
inner join candidate as c
on v.id = c.id;

select v.name, b.number
from voter as v
inner join bulletin as b
on v.id = b.id;

select c.name, v.name
from candidate as c
inner join voter as v
on c.id = v.id;

select v.name, b.candidate_id
from voter as v
inner join bulletin as b
on v.id = b.id;

select b.candidate_id, c.name
from bulletin as b
inner join candidate as c
on b.id = c.id;



