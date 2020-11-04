create table users (
    id                    bigserial,
    username              varchar(30) not null,
    password              varchar(80) not null,
    email                 varchar(50) unique,
    created_at            timestamp default current_timestamp,
    updated_at            timestamp default current_timestamp,
    primary key (id)
);

create table roles (
    id                    serial,
    name                  varchar(50) not null,
    created_at            timestamp default current_timestamp,
    updated_at            timestamp default current_timestamp,
    primary key (id)
);

CREATE TABLE users_roles (
    user_id               bigint not null,
    role_id               int not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

insert into roles (name)
values
('ROLE_USER'),
('ROLE_ADMIN'),
('DELETE_USERS_PERMISSION');

insert into users (username, password, email)
values
('Bob', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com'),
('John Johnson', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 1),
(1, 3);

create table authors (
    id                  bigserial primary key,
    name                varchar(255),
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);

insert into authors (name)
values
('Rowling');

create table books (
    id                  bigserial primary key,
    title               varchar(255),
    description         varchar(5000),
    genre               varchar(255),
    author_id           bigint,
    price               numeric(8, 2),
    publish_year        int,
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp,
    foreign key (author_id) references authors (id)
);

insert into books (title, description, genre, author_id, price, publish_year)
values
('Harry Potter: Philosopher''s Stone', 'Description 1', 'FANTASY', 1, 300.0, 2000);

drop table if exists orders cascade;
create table orders (
    id bigserial,
    user_id bigint not null,
    price numeric(8, 2) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key(id),
    foreign key (user_id) references users (id)
);

drop table if exists orders_items cascade;
create table orders_items (
    id bigserial, order_id bigint not null,
    book_id bigint not null,
    quantity int,
    price numeric(8, 2),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key(id),
    foreign key (book_id) references books (id),
    foreign key (order_id) references orders (id)
);

