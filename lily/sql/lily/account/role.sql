
    create sequence role_seq start with 1000;

    create table "role"(
        -- 0 for root role
        id          int primary key default nextval('role_seq'),
        name        varchar(32) not null unique,

        --\mixin lily.mixin.LabelExVer
        --\mixin lily.mixin.Props

        dummy       int
    );

    create index role_label        on "role"(label);
    create index role_lastmod      on "role"(lastmod desc);
    create index role_state        on "role"(state);
    
    insert into "role"(id, name, label) values(0, 'manage', 'Manage');
