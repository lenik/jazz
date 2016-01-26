--\import lily.base.form

    create sequence formcp_seq start with 10000;

    create table formcp(            -- creation parameters
        id          int primary key default nextval('formcp_seq'),
        form        int not null
            references form(id) on update cascade on delete cascade,
        name        varchar(30),
        value       varchar(100),

        constraint formcp_uk unique(form, name)
    );
