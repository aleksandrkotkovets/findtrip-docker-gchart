
INSERT INTO app_role (id, role) VALUES ( (SELECT nextval('hibernate_sequence')) ,'ROLE_ADMIN');
INSERT INTO app_role (id, role) VALUES ( (SELECT nextval('hibernate_sequence')), 'ROLE_CLIENT');
INSERT INTO app_role (id, role) VALUES ( (SELECT nextval('hibernate_sequence')), 'ROLE_WORKER');