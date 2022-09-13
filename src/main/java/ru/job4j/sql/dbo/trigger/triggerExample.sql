    create table products (
        id serial primary key,
        name varchar(50),
        producer varchar(50),
        count integer default 0,
        price integer
    );

    create or replace function discount()
        returns trigger as
    $$
        begin
            update products
            set price = price - price * 0.2
            where count <= 5 and id = new.id;
            return new;
        end;
    $$
    LANGUAGE 'plpgsql';

    create trigger discount_trigger
        after insert
        on products
        for each row
        execute procedure discount();

    insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);
    SELECT * FROM products;

    insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
    SELECT * FROM products;

    create or replace function tax()
        returns trigger as
    $$
        begin
            update products
            set price = price - price * 0.2
            where id = (select id from inserted) and count <= 5;
            return new;
        end;
    $$
    LANGUAGE 'plpgsql';

    create trigger tax_trigger
        after insert on products
        referencing new table as inserted
        for each statement
        execute procedure tax();

    SELECT * FROM products;

    create or replace function taxForAllProductsAndAllTableUpdate()
        returns trigger as
    $$
        begin
            update products
            set price = price - price * 0.2
            where id = (select id from inserted);
            return new;
        end;
    $$
    LANGUAGE 'plpgsql';

    create trigger tax_trigger_for_all_products_after_insert_and_all_table_at_once()
        after insert on products
        referencing new table as inserted
        for each statement
        execute procedure taxForAllProductsAndAllTableUpdate();

    insert into products (name, producer, count, price) VALUES ('product_6', 'producer_6', 6, 1000);
    SELECT * FROM products;

    insert into products (name, producer, count, price) VALUES ('product_11', 'producer_11', 11, 110);
    SELECT * FROM products;

    create or replace function taxForAllProductsAndEveryRowUpdate()
        returns trigger as
    $$
        begin
            update products
            new.price = new.price + new.price * 0.1;
            return new;
            return new;
        end;
    $$
    LANGUAGE 'plpgsql';

    create trigger tax_trigger_for_all_products_before_insert_and_line_by_line
        before insert
        on products
        for each row
        execute procedure taxForAllProductsAndEveryRowUpdate();

    insert into products (name, producer, count, price) VALUES ('product_12', 'producer_12', 12, 500);
    SELECT * FROM products;

    create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
    );

    create or replace function add_entry_to_history_of_price()
        returns trigger as
    $$
        BEGIN
        insert into history_of_price(name, price, date)
        values(new.name, new.price, now());
        return new;
        END;
    $$
    LANGUAGE 'plpgsql';

    create trigger add_entry_to_history_of_price_trigger
        after insert on products
        for each row
        execute procedure add_entry_to_history_of_price();

    insert into products (name, producer, count, price) VALUES ('product_13', 'producer_13', 13, 800);
    SELECT * FROM history_of_price;



