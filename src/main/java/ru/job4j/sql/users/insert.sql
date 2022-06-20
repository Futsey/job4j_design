insert into new_rules (name)
values
('Создать'),
('Копировать'),
('Вставить'),
('Удалить')
;

insert into new_role (role)
values
('Ученик'),
('Преподаватель')
;

insert into new_user (name, role_id)
values
('Андрей', 1),
('Лана', 2)
;

insert into new_comments (name)
values
('Хорошо понимает предмет'),
('Плохо понимает предмет')
;

insert into new_attachs (name)
values
('Текст'),
('Фото'),
('Видео')
;

insert into new_item (name, user_id, comments_id, attachs_id)
values
('Заявка на кодревью', 1, 2, 1),
('Кодревью', 2, 1, 1)
;

insert into new_category (name, item)
values
('Хорошая работа', 2),
('Хорошая работа, но передалать', 1)
;

insert into new_state (name, tem)
values
('Отлично', 2),
('ГУГОЛ В ПОМОЩЬ!!!!', 1)
;