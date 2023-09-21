insert into users(username, password, first_name, last_name, email, telephone, roles, created_at, modified_at)
values ('SuperAdmin', '$2a$10$QQo1hn1mdzFrDvriAkQ/X.QrSe6Q6oVOiTbjC9skBl1AJ5LAPnfku', 'Super', 'admin', 'superadmin@example.com', '0661234567', ARRAY ['USER','ADMIN','SUPERADMIN'], NOW(), NOW())
ON CONFLICT (username) DO NOTHING;