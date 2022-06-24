Правило!!! Внешний ключ всегда ставится со стороны many

create table new_rules (
    id serial primary key,
    name text
);

create table new_role (
    id serial primary key,
    role text
);

create table rule_role (
    id serial primary key,
    rule_role text,
    new_rules_id int references new_rules(id),
    new_role_id int references new_role(id)
);

create table new_category (
    id serial primary key,
    name text
);

create table new_state (
    id serial primary key,
    name text
);

create table new_user (
    id serial primary key,
    name varchar(255),
    fk_role_id int references new_role(id)
);

create table new_item (
    id serial primary key,
    name text,
    fk_user_id int references new_user(id),
    fk_new_category_id int references new_category(id),
    fk_new_state_id int references new_state(id)
);

create table new_comments (
    id serial primary key,
    name text,
    fk_item_id int references new_item(id)
);

create table new_attachs (
    id serial primary key,
    name text,
    fk_item_id int references new_item(id)
);

