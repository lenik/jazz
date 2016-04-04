--\import lily.base._form

    create sequence _formparm_seq start with 10000;

    -- creation parameters
    create table _formparm(
        id          int primary key default nextval('_formparm_seq'),
        form        int not null
            references _form(id) on update cascade on delete cascade,
        
        -- parameter name
        name        varchar(30),
        
        -- default value
        value       varchar(100),

        constraint _formparm_uk unique(form, name)
    );
