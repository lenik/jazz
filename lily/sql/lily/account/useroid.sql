--\import lily.account.user
--\import lily.account.useroidtype

    create sequence useroid_seq start with 1000;

    create table useroid(
        id          int primary key default nextval('useroid_seq'),

        -- label: cached label on other systems
        -- state: verified?
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.MomentInterval
--\mixin lily.mixin.Props

        "user"      int not null
            references "user" on update cascade on delete cascade,

        -- openid, etc.
        "type"      int not null
            references useroidtype on update cascade,

        -- actual id in the id provider.
        oid         varchar(100) not null,

        auth        jsonb
    );

    create index useroid_lastmod        on useroid(lastmod desc);
    create index useroid_user           on useroid("user");
    create index useroid_type           on useroid("type");
    create index useroid_oid            on useroid(oid);
    
    comment on table useroid is 'User Open ID';
    comment on column useroid."user" is 'User: The declaring user';
    comment on column useroid."type" is 'Type: Type of Open ID';
    comment on column useroid.oid is 'OID: The identity data';
    comment on column useroid.auth is 'Auth Data: The authentication data';
