create table product_type
(
    id               bigint           not null
        constraint product_type_pkey
            primary key,
    base_price       double precision not null,
    description      varchar(255)     not null,
    lessons_hours    integer          not null,
    name             varchar(50)      not null,
    product_category varchar(20)      not null
);


create table student
(
    id           bigint      not null
        constraint student_pkey
            primary key,
    created_by   bigint,
    created_date timestamp default now(),
    email        varchar(50) not null,
    first_name   varchar(50) not null,
    last_name    varchar(50) not null,
    phone_number integer     not null
);


create table product
(
    id              bigint           not null
        constraint product_pkey
            primary key,
    book_online     boolean,
    hours_left      integer          not null,
    is_paid         boolean,
    price           double precision not null,
    product_type_id bigint           not null
        constraint fk_product_type_id
            references product_type,
    student_id_id   bigint           not null
        constraint fk_student_id
            references student
);


create table user_app
(
    id           bigint       not null
        constraint user_app_pkey
            primary key,
    created_date timestamp default now(),
    email        varchar(50)  not null
        constraint user_email_unique
            unique,
    first_name   varchar(50)  not null,
    last_name    varchar(50)  not null,
    password     varchar(120) not null,
    phone_number integer      not null,
    username     varchar(20)  not null
        constraint user_username_unique
            unique
);


create table lesson
(
    id            bigint not null
        constraint lesson_pkey
            primary key,
    date          date,
    time_end      timestamp,
    time_start    timestamp,
    instructor_id bigint not null
        constraint fk_instructor_id
            references user_app,
    product_id    bigint not null
        constraint fk_product_id
            references product
);


create table user_role
(
    id   serial not null
        constraint user_role_pkey
            primary key,
    name varchar(20)
        constraint user_role_unique
            unique
);


create table user_roles_list
(
    user_id bigint  not null
        constraint fk_user_id
            references user_app,
    role_id integer not null
        constraint fk_role_id
            references user_role,
    constraint user_roles_list_pkey
        primary key (user_id, role_id)
);


create table work_schedule
(
    id            bigint not null
        constraint work_schedule_pkey
            primary key,
    date          date,
    time_end      timestamp,
    time_start    timestamp,
    instructor_id bigint not null
        constraint fk_instructor_id
            references user_app
);

INSERT INTO user_role(name)
VALUES ('ROLE_ADMIN');
INSERT INTO user_role(name)
VALUES ('ROLE_MODERATOR');
INSERT INTO user_role(name)
VALUES ('ROLE_STUDENT');
INSERT INTO user_role(name)
VALUES ('ROLE_INSTRUCTOR');

