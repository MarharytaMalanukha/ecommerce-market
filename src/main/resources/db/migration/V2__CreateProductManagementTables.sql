CREATE TABLE product_categories (
    id bigserial primary key,
    name varchar(30) unique not null,
    description text,
    main_product_category_id bigint,
    created_at timestamp not null,
    modified_at timestamp not null,
    FOREIGN KEY (main_product_category_id) REFERENCES product_categories(id)
);

CREATE TABLE product_discounts (
    id bigserial primary key,
    name varchar(30) unique not null,
    description text,
    discount_percent decimal not null,
    active boolean default true not null,
    created_at timestamp not null,
    modified_at timestamp not null
);

CREATE TABLE products (
    id bigserial primary key,
    sku varchar(50) unique not null,
    name varchar(50) not null,
    description text,
    image_url text,
    quantity integer not null,
    price_euro decimal not null,
    product_category_id bigint not null,
    product_discount_id bigint not null,
    created_at timestamp not null,
    modified_at timestamp not null,
    FOREIGN KEY (product_category_id) REFERENCES product_categories(id),
    FOREIGN KEY (product_discount_id) REFERENCES product_discounts(id)
);