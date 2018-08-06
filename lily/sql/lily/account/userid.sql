--\import lily.account.user
--\import lily.account.useridtype

    create sequence userid_seq start with 1000;

    create table userid(
        id          int primary key default nextval('userid_seq'),
        "user"      int not null
            references "user"(id) on update cascade on delete cascade,

        -- openid, etc.
        type        int not null
            references useridtype(id) on update cascade,

        -- actual id in the id provider.
        oid         varchar(100) not null,

        auth        jsonb,
        data        jsonb
    );
