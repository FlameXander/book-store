create table users (
    id                      bigserial primary key,
    username                varchar(30) not null,
    password                varchar(80) not null,
    email                   varchar(50) unique,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

create table roles (
    id                      bigserial primary key,
    name                    varchar(50) not null,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

CREATE TABLE users_roles (
    user_id                 bigint not null references users (id),
    role_id                 bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into roles (name)
values
('ROLE_USER'),
('ROLE_ADMIN');

insert into users (username, password, email)
values
('bob', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com'),
('john', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 2);

create table authors (
    id                      bigserial primary key,
    name                    varchar(255) not null,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

insert into authors (name)
values
('Rowling');

create table genres (
    id                      bigserial primary key,
    title                   varchar(255) not null,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

insert into genres (title)
values
('Фэнтези'),
('Научная фантастика'),
('Детектив');

create table books (
    id                      bigserial primary key,
    title                   varchar(255) not null,
    description             varchar(5000),
    genre_id                bigint references genres (id),
    author_id               bigint references authors (id),
    price                   numeric(8, 2) not null,
    publish_year            int,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

insert into books (title, description, genre_id, author_id, price, publish_year)
values
('Гарри Поттер и Философский камень', '-', 1, 1, 300.0, 2000),
('Гарри Поттер и Тайная комната', '-', 1, 1, 400.0, 2001),
('Гарри Поттер и Узник Азкабана', '-', 1, 1, 500.0, 2002),
('Гарри Поттер и Кубок огня', '-', 1, 1, 700.0, 2007),
('Гарри Поттер и Орден феникса', '-', 1, 1, 440.0, 2004),
('Гарри Поттер и Принц полукровка', '-', 1, 1, 650.0, 2007),
('Гарри Поттер и Дары смерти', '-', 1, 1, 650.0, 2007),
('Агенство "Локвуд и компания": Кричащая лестница', '-', 1, 1, 200.0, 2006),
('Никогде', '-', 1, 1, 200.0, 2000),
('Рожденный туманом', '-', 1, 1, 200.0, 2000),
('Хроники Эмбера: Десять прицнев Эмбера', '-', 1, 1, 200.0, 1989),
('Властелин колец: Братство кольца', '-', 1, 1, 1200.0, 2006),
('Властелин колец: Две твердыни', '-', 1, 1, 900.0, 2004),
('Властелин колец: Возвращение короля', '-', 1, 1, 600.0, 2001),
('Хоббит', '-', 1, 1, 500.0, 2001);

drop table if exists orders cascade;
create table orders (
    id                      bigserial primary key,
    user_id                 bigint not null references users (id),
    price                   numeric(8, 2) not null,
    address                 varchar(255) not null,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

drop table if exists orders_items cascade;
create table orders_items (
    id                      bigserial primary key,
    order_id                bigint not null references orders (id),
    book_id                 bigint not null references books (id),
    quantity                int not null,
    price_per_item          numeric(8, 2) not null,
    price                   numeric(8, 2) not null,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

insert into orders (user_id, price, address)
values
(1, 100, 'abcd'),
(2, 200, 'efgh');

insert into orders_items (order_id, book_id, quantity, price_per_item, price)
values
(1, 1, 1, 100, 100),
(2, 2, 4, 50, 200);