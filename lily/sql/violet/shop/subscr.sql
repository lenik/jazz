--\import lily.account
--\mixin lily.template.a-cat subscr

    -- Membership subscription
    
    create sequence subscr_seq start with 1000;
    create table subscr(
        id          int primary key default nextval('subscr_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.Ver
--\mixin lily.mixin.Mi

        cat         int not null
            references subscrcat(id) on update cascade on delete cascade,

        "user"      int not null
            references "user"(id) on update cascade on delete cascade
    );

    create index subscr_lastmod     on subscr(lastmod desc);
    create index subscr_uid_acl     on subscr(uid, acl);
