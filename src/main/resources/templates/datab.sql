-- Сначала просто запусти приложение, таблицы создадутся автоматически


INSERT INTO Role (name, role_id) VALUES ('ROLE_USER', 1);
INSERT INTO Role (name, role_id) VALUES ('ROLE_ADMIN', 2);

INSERT INTO User (name, password, surname) VALUES ('admin', 'admin', 'admin');
INSERT INTO User (name, password, surname) VALUES ('user', 'user', 'user');

insert into user_role (user_user_id, role_role_id) VALUES (1,2);
insert into user_role (user_user_id, role_role_id) VALUES (2,1);