create table if not exists application_users
(
    id       uuid primary key,
    username varchar(255) not null,
    name     varchar(255) not null,
    password varchar(255) not null,
    role     varchar(255) not null
);

create table if not exists cv_applications
(
    id                uuid primary key,
    chat_id           bigint                  not null,
    linked_in_link    varchar(255)            not null,
    full_name         varchar(255)            not null,
    cv_link           varchar(255)            not null,
    telegram_username varchar(255)            not null,
    status            varchar(255)            not null,
    assignee          uuid,
    created_at        timestamp default now(),
    updated_at        timestamp default now(),
    to_remove         boolean   default false not null,
    foreign key (assignee) references application_users (id)
);
