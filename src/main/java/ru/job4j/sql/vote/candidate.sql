create table candidate (
id serial primary key,
    name varchar(255),
	political_party text,
	constituency smallint
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

select v.name, c.name, c.political_party
from voter as v
inner join candidate as c
on v.id = c.id;

select v.name, c.political_party
from voter as v
inner join candidate as c
on v.id = v.id;