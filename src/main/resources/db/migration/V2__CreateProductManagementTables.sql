CREATE TABLE main_product_categories (
    id bigserial primary key,
    name varchar(30) not null,
    created_at timestamp not null,
    modified_at timestamp not null
);

CREATE TABLE product_categories (
    id bigserial primary key,
    name varchar(30) not null,
    description text,
    main_product_category_id bigint not null,
    created_at timestamp not null,
    modified_at timestamp not null,
    FOREIGN KEY (main_product_category_id) REFERENCES main_product_categories(id)
);

CREATE TABLE product_discounts (
    id bigserial primary key,
    name varchar(30) not null,
    description text,
    discount_percent decimal not null,
    active boolean default true,
    created_at timestamp not null,
    modified_at timestamp not null
);

CREATE TABLE product_inventory (
    id bigserial primary key,
    quantity integer not null,
    created_at timestamp not null,
    modified_at timestamp not null
);

CREATE TABLE products (
    id bigserial primary key,
    sku varchar(50) not null,
    name varchar(50) not null,
    description text,
    image_url text,
    price_euro decimal not null,
    product_category_id bigint not null,
    product_inventory_id bigint not null,
    product_discount_id bigint,
    created_at timestamp not null,
    modified_at timestamp not null,
    FOREIGN KEY (product_category_id) REFERENCES product_categories(id),
    FOREIGN KEY (product_inventory_id) REFERENCES product_inventory(id),
    FOREIGN KEY (product_discount_id) REFERENCES product_discounts(id)
);