INSERT INTO app_user(id,
                     email,
                     first_name,
                     last_name,
                     login,
                     password,
                     patronymic,
                     phone_number,
                     role_id)
VALUES ((SELECT nextval('hibernate_sequence')),
        'kotkovetss2000@gmail.com',
        'Александр',
        'Котковец',
        'admin',
        'adminadmin',
        'Сергеевич',
        '375(33)3885668',
        (SELECT app_role.id FROM app_role WHERE app_role.role = 'ROLE_ADMIN' LIMIT 1)
)

