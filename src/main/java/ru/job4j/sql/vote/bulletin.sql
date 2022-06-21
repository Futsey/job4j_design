create table bulletin (
id serial primary key,
    number int,
	candidate_id int references candidate(id),
	voter_id int references voter(id)
);

insert into bulletin(number, candidate_id, voter_id) values (1, 9, 1);
insert into bulletin(number, candidate_id, voter_id) values (2, 9, 2);
insert into bulletin(number, candidate_id, voter_id) values (3, 10, 3);
insert into bulletin(number, candidate_id, voter_id) values (4, 11, 4);
insert into bulletin(number, candidate_id, voter_id) values (5, 12, 5);
insert into bulletin(number, candidate_id, voter_id) values (6, 13, 6);

select bulletin.number, candidate.name
from bulletin
inner join candidate on candidate_id = candidate.id;

select bulletin.number, bulletin.voter_id, candidate.name, candidate.political_party
from bulletin
inner join candidate on candidate_id = candidate.id;

select bulletin.number, voter.name, bulletin.candidate_id, candidate.name, candidate.political_party
from bulletin
inner join candidate on candidate_id = candidate.id
inner join voter on voter_id = voter.id;

select b.number, b.candidate_id, v.name
from bulletin as b
inner join voter as v
on v.id = b.id;


