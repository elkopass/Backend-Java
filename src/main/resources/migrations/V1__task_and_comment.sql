create table projects
(
    id          varchar(255) not null
        primary key,
    create_date date,
    description varchar(255),
    edit_date   date,
    name        varchar(255)
);

alter table projects
    owner to hits;

create table users
(
    id          varchar(255) not null
        primary key,
    create_date date,
    edit_date   date,
    password    varchar(255),
    role        varchar(255),
    sno         varchar(255),
    username    varchar(255)
);

alter table users
    owner to hits;

create table comments
(
    id           varchar(255) not null
        primary key,
    comment_text varchar(255),
    create_date  date,
    edit_date    date,
    user_id      varchar(255)
        constraint fk8omq0tc18jd43bu5tjh6jvraq
            references users
);

alter table comments
    owner to hits;

create table tasks
(
    id          varchar(255) not null
        primary key,
    create_date date,
    description varchar(255),
    edit_date   date,
    mark        varchar(255),
    name        varchar(255),
    priority    varchar(255),
    creator_id  varchar(255)
        constraint fkt1ph5sat39g9lpa4g5kl46tbv
            references users,
    executer_id varchar(255)
        constraint fk247r7da09bk9g1mk828hfn64p
            references users,
    project_id  varchar(255)
        constraint fksfhn82y57i3k9uxww1s007acc
            references projects
);

alter table tasks
    owner to hits;

create table task_and_comments
(
    comment_id varchar(255) not null
        constraint fkofp5cfd0obsxkhu83r04h1hkg
            references comments,
    task_id    varchar(255) not null
        constraint fk8q6hlmhr2b76oo71sv5ppg7qt
            references tasks
);

alter table task_and_comments
    owner to hits;

