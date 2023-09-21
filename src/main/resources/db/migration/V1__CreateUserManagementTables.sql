CREATE TABLE users (
    id bigserial primary key,
    username varchar(50) unique not null,
    password text not null,
    first_name varchar(30) not null,
    last_name varchar(30) not null,
    email varchar(100) not null,
    telephone varchar(20) not null,
    profile_picture text,
    roles text[] not null,
    created_at timestamp not null,
    modified_at timestamp not null
);

CREATE TABLE user_addresses (
    id bigserial primary key,
    user_id bigint not null,
    address_line varchar(150) not null,
    city varchar(30) not null,
    postal_code varchar(10) not null,
    country varchar(50) not null,
    FOREIGN key (user_id) REFERENCES users(id)
);

CREATE TABLE user_payments (
    id bigserial primary key,
    user_id bigint not null,
    payment_type varchar(30) not null,
    provider varchar(30) not null,
    account_number varchar(30) not null,
    expiry timestamp not null,
    FOREIGN key (user_id) REFERENCES users(id)
);