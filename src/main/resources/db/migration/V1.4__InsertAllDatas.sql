
-- INSERT INTO public.app_user (id, email, first_name, last_name, login, password, patronymic, phone_number, role_id, wallet_id) VALUES (4, 'kotkovetss2000@gmail.com', 'Александр', 'Котковец', 'admin', 'adminadmin', 'Сергеевич', '375(33)3885668', 1, null);
-- INSERT INTO public.app_user (id, email, first_name, last_name, login, password, patronymic, phone_number, role_id, wallet_id) VALUES (15, 'worker@worker', 'Worker', 'Worker', 'worker', 'workerworker', 'Worker', '375(33)3993423', 3, null);
-- INSERT INTO public.app_user (id, email, first_name, last_name, login, password, patronymic, phone_number, role_id, wallet_id) VALUES (19, 'sdfsdf@sdf', 'Fsdfsdf', 'Fsdhsd', 'client', 'clientclient', 'Fsdfsdf', '375(33)2346234', 2, null);

-- INSERT INTO public.country (id, name) VALUES (5, 'Belarus');
-- INSERT INTO public.country (id, name) VALUES (6, 'Russia');
-- INSERT INTO public.country (id, name) VALUES (26, 'Poland');
-- INSERT INTO public.country (id, name) VALUES (27, 'Latvia');
INSERT INTO public.country (id, name) VALUES (100, 'Ukrain');
INSERT INTO public.country (id, name) VALUES (101, 'UK');

-- INSERT INTO public.city (id, name, country_id) VALUES (7, 'Minsk', 5);
-- INSERT INTO public.city (id, name, country_id) VALUES (8, 'Brest', 5);
-- INSERT INTO public.city (id, name, country_id) VALUES (9, 'Moskow', 6);
-- INSERT INTO public.city (id, name, country_id) VALUES (10, 'Piter', 6);
-- INSERT INTO public.city (id, name, country_id) VALUES (28, 'Riga', 27);
-- INSERT INTO public.city (id, name, country_id) VALUES (29, 'Elgava', 27);
-- INSERT INTO public.city (id, name, country_id) VALUES (30, 'Warzawa', 26);
-- INSERT INTO public.city (id, name, country_id) VALUES (31, 'Lodz', 26);
INSERT INTO public.city (id, name, country_id) VALUES (200, 'Kiev', 100);
INSERT INTO public.city (id, name, country_id) VALUES (201, 'Donetsk', 100);
INSERT INTO public.city (id, name, country_id) VALUES (202, 'London', 101);
INSERT INTO public.city (id, name, country_id) VALUES (203, 'Wales', 101);

-- INSERT INTO public.airport (id, code, name, city_id) VALUES (11, 'DMD', 'Domodedovo', 9);
-- INSERT INTO public.airport (id, code, name, city_id) VALUES (12, 'STO', 'Sherementevo', 9);
-- INSERT INTO public.airport (id, code, name, city_id) VALUES (13, 'MSQ', 'MINSK-1', 7);
-- INSERT INTO public.airport (id, code, name, city_id) VALUES (14, 'MNA', 'MINSK Nationall', 7);
-- INSERT INTO public.airport (id, code, name, city_id) VALUES (32, 'WMI', 'Warzawa National', 30);
-- INSERT INTO public.airport (id, code, name, city_id) VALUES (33, 'WAW', 'Shopen', 30);
-- INSERT INTO public.airport (id, code, name, city_id) VALUES (43, 'QWE', 'Latv1', 28);
-- INSERT INTO public.airport (id, code, name, city_id) VALUES (44, 'TRW', 'Latv2', 29);
-- INSERT INTO public.airport (id, code, name, city_id) VALUES (45, 'TWE', 'Latv3', 28);
-- INSERT INTO public.airport (id, code, name, city_id) VALUES (46, 'QTS', 'Latv4', 29);
INSERT INTO public.airport (id, code, name, city_id) VALUES (300, 'KIV', 'Kiev1', 200);
INSERT INTO public.airport (id, code, name, city_id) VALUES (301, 'KEV', 'Kiev2', 200);
INSERT INTO public.airport (id, code, name, city_id) VALUES (302, 'DON', 'Donetsk1', 201);
INSERT INTO public.airport (id, code, name, city_id) VALUES (303, 'DNS', 'Donetsk2', 201);
INSERT INTO public.airport (id, code, name, city_id) VALUES (304, 'LON', 'London1', 202);
INSERT INTO public.airport (id, code, name, city_id) VALUES (305, 'LOD', 'London2', 202);

-- INSERT INTO public.company (id, name, rating) VALUES (16, 'Belavia', 'FOUR_STARS');
-- INSERT INTO public.company (id, name, rating) VALUES (34, 'Pobeda', 'THREE_STARS');


--
-- INSERT INTO public.plane (id, name, side_number, company_id) VALUES (35, 'Boing', 'P1111', 34);
-- INSERT INTO public.plane (id, name, side_number, company_id) VALUES (36, 'Boing', 'P1122', 34);
-- INSERT INTO public.plane (id, name, side_number, company_id) VALUES (37, 'Boing', 'B2233', 16);
-- INSERT INTO public.plane (id, name, side_number, company_id) VALUES (17, 'Boing', 'B2244', 16);
