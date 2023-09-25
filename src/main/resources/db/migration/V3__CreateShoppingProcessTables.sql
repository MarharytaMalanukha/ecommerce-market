CREATE TABLE shopping_sessions (
    id bigserial primary key,
    user_id bigint not null,
    total decimal not null,
    created_at timestamp not null,
    modified_at timestamp not null,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE cart_items (
    id bigserial primary key,
    shopping_session_id bigint not null,
    product_id bigint not null,
    quantity integer not null,
    created_at timestamp not null,
    modified_at timestamp not null,
    FOREIGN KEY (shopping_session_id) REFERENCES shopping_sessions(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE order_payments (
    id bigserial primary key,
    amount decimal not null,
    user_payment_id bigint,
    payment_status varchar(20) not null,
    created_at timestamp not null,
    modified_at timestamp not null,
    FOREIGN KEY (user_payment_id) REFERENCES user_payments(id)
);

CREATE TABLE orders (
    id bigserial primary key,
    user_id bigint not null,
    payment_id bigint not null,
    user_address_id bigint,
    total decimal not null,
    created_at timestamp not null,
    modified_at timestamp not null,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (payment_id) REFERENCES order_payments(id)
);

CREATE TABLE order_items (
    id bigserial primary key,
    order_id bigint not null,
    product_id bigint not null,
    quantity integer not null,
    created_at timestamp not null,
    modified_at timestamp not null,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);