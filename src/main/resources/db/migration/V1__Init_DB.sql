create table app_role
(
    id   bigint not null
        constraint app_role_pkey
            primary key,
    role varchar(12)
);

create table company
(
    id     bigint       not null
        constraint company_pkey
            primary key,
    name   varchar(30)  not null,
    rating varchar(255) not null
);

create table country
(
    id   bigint not null
        constraint country_pkey
            primary key,
    name varchar(40)
);

create table city
(
    id         bigint not null
        constraint city_pkey
            primary key,
    name       varchar(255),
    country_id bigint
        constraint fkrpd7j1p7yxr784adkx4pyepba
            references country
);

create table airport
(
    id      bigint      not null
        constraint airport_pkey
            primary key,
    code    varchar(5)  not null,
    name    varchar(30) not null,
    city_id bigint      not null
        constraint fkf583ieaw0ttnwklqy222tfru0
            references city
);

create table plane
(
    id          bigint      not null
        constraint plane_pkey
            primary key,
    name        varchar(30) not null,
    side_number varchar(10) not null,
    company_id  bigint      not null
        constraint fkbtj8q7xla3aojudiebq9v4c3u
            references company
);

create table flight
(
    id                   bigint           not null
        constraint flight_pkey
            primary key,
    all_seats            integer          not null,
    arrival_date         timestamp        not null,
    departure_date       timestamp        not null,
    free_seats           integer          not null,
    price                double precision not null,
    status               varchar(8),
    airport_arrival_id   bigint           not null
        constraint fkd3fqo2trwxe0ncbpbft8k3nic
            references airport,
    airport_departure_id bigint           not null
        constraint fkl6gbor3on968mydp63h8c3h2
            references airport,
    plane_id             bigint           not null
        constraint fk7p9fvp6d7uh9cgn47uet8a8nb
            references plane
);

create table wallet
(
    id  bigint           not null
        constraint wallet_pkey
            primary key,
    sum double precision not null
);

create table app_user
(
    id           bigint      not null
        constraint app_user_pkey
            primary key,
    email        varchar(40),
    first_name   varchar(15) not null,
    last_name    varchar(15) not null,
    login        varchar(15) not null,
    password     varchar(15) not null,
    patronymic   varchar(15) not null,
    phone_number varchar(15) not null,
    role_id      bigint      not null
        constraint fkn1w45cvkd2bdpvu78k056mckg
            references app_role,
    wallet_id    bigint
        constraint fkkd7wjyvdw46qjkf4e4wwxlf21
            references wallet
);

create table app_order
(
    id         bigint           not null
        constraint app_order_pkey
            primary key,
    final_cost double precision not null,
    order_date timestamp        not null,
    status     varchar(8),
    flight_id  bigint           not null
        constraint fk613k84djcsqwrkf4l5e75gdhd
            references flight,
    user_id    bigint           not null
        constraint fkesrwi8i75102xo4k9ykicpp7v
            references app_user
);

create table ticket
(
    id       bigint           not null
        constraint ticket_pkey
            primary key,
    price    double precision not null,
    order_id bigint           not null
        constraint fk4a0qi9ycuabued589neh11sdn
            references app_order
);

create sequence hibernate_sequence
  start 1
  increment 1;

