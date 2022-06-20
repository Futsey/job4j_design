create table new_rules (
    id serial primary key,
    name text
);

create table new_role (
    id serial primary key,
    role text
);

create table new_user (
    id serial primary key,
    name varchar(255),
    role_id int references new_role(id)
);

create table new_comments (
    id serial primary key,
    name text
);

create table new_attachs (
    id serial primary key,
    name text
);

create table new_item (
    id serial primary key,
    name text,
    user_id int references new_user(id),
    comments_id int references new_comments(id),
    attachs_id int references new_attachs(id)
);

create table new_category (
    id serial primary key,
    name text,
    item int references new_item(id)
);

create table new_state (
    id serial primary key,
    name text,
    tem int references new_item(id)
);