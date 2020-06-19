--\import lily.account.user

    create sequence useradd_seq start with 1000;
    create table useradd(
        id          int primary key default nextval('useradd_seq'),

--\mixin lily.mixin.Label
        -- a friendly message

--\mixin lily.mixin.Ex
        -- state: INIT, ACCEPT, REJECT

--\mixin lily.mixin.Ver
        -- timestamp of the request

        whom        int not null
            references "user" on update cascade on delete cascade,

        response    varchar(200)
    );

    create index useradd_state      on useradd(state);
