INSERT INTO category(name)
VALUES ('Eurogames');
INSERT INTO category(name)
VALUES ('Ameritrash');
INSERT INTO category(name)
VALUES ('Familiar');

INSERT INTO author(name, nationality)
VALUES ('Alan R. Moon', 'US');
INSERT INTO author(name, nationality)
VALUES ('Vital Lacerda', 'PT');
INSERT INTO author(name, nationality)
VALUES ('Simone Luciani', 'IT');
INSERT INTO author(name, nationality)
VALUES ('Perepau Llistosella', 'ES');
INSERT INTO author(name, nationality)
VALUES ('Michael Kiesling', 'DE');
INSERT INTO author(name, nationality)
VALUES ('Phil Walker-Harding', 'US');

INSERT INTO game(title, age, category_id, author_id)
VALUES ('On Mars', '14', 1, 2);
INSERT INTO game(title, age, category_id, author_id)
VALUES ('Aventureros al tren', '8', 3, 1);
INSERT INTO game(title, age, category_id, author_id)
VALUES ('1920: Wall Street', '12', 1, 4);
INSERT INTO game(title, age, category_id, author_id)
VALUES ('Barrage', '14', 1, 3);
INSERT INTO game(title, age, category_id, author_id)
VALUES ('Los viajes de Marco Polo', '12', 1, 3);
INSERT INTO game(title, age, category_id, author_id)
VALUES ('Azul', '8', 3, 5);
INSERT INTO game(title, age, category_id, author_id)
VALUES ('Overwach', '18', 1, 4);

INSERT INTO client(name)
VALUES ('Alba');
INSERT INTO client(name)
VALUES ('Carlos');
INSERT INTO client(name)
VALUES ('Manuel');
INSERT INTO client(name)
VALUES ('Paula');
INSERT INTO client(name)
VALUES ('Claudia');
INSERT INTO client(name)
VALUES ('Jose');
INSERT INTO client(name)
VALUES ('Alberto');

INSERT INTO prestamo(game_id, client_id, client_id, date_ini, date_fin)
VALUES (1, 1, 1, '09/01/2025', '14/01/2025');
INSERT INTO prestamo(game_id, client_id, client_id, date_ini, date_fin)
VALUES (2, 2, 2, '09/01/2026', '14/01/2026');
INSERT INTO prestamo(game_id, client_id, client_id, date_ini, date_fin)
VALUES (3, 3, 1, '20/01/2026', '29/01/2026');
INSERT INTO prestamo(game_id, client_id, client_id, date_ini, date_fin)
VALUES (4, 4, 1, '05/02/2026', '14/02/2026');
INSERT INTO prestamo(game_id, client_id, client_id, date_ini, date_fin)
VALUES (6, 5, 1, '30/05/2026', '03/06/2026');
INSERT INTO prestamo(game_id, client_id, client_id, date_ini, date_fin)
VALUES (7, 6, 3, '07/10/2026', '16/10/2026');