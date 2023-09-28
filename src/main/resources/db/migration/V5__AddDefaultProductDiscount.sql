insert into product_discounts(name, description, discount_percent, active, created_at, modified_at)
values ('NONE', 'Placeholder for products with no discount', 0, true, NOW(), NOW())
ON CONFLICT (name) DO NOTHING;