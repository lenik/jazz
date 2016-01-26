--\import lily.account.user

    create sequence userid_seq start with 1000;

    create table userid(
        id          int primary key default nextval('userid_seq'),
        "user"      int not null
            references "user"(id) on update cascade on delete cascade,

        -- openid, etc.
        type        varchar(20) not null,

        -- actual id in the id provider.
        oid         varchar(100) not null,

        auth        jsonb,
        data        jsonb
    );

