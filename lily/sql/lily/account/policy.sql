--\import lily.account.user

    create sequence policy_seq start with 1000;
    create table policy(
        id          int primary key default nextval('policy_seq'),
--\mixin lily.mixin.Code
--\mixin lily.mixin.Label
        
        gid         int null,
        constraint fk_gid foreign key(gid)
            references "group"(id) on update cascade on delete set null,
        
        uid         int null,
        constraint fk_uid foreign key(uid)
            references "user"(id) on update cascade on delete set null,
        
        class       varchar(80) not null,
        method      varchar(40) null,
        allow       int not null default 0,
        deny        int not null default 0,

        constraint uk_4 unique(gid, uid, class, method)
    );

    create index policy_code        on policy(code);
    create index policy_label       on policy(label);
