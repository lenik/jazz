--\import lily.account

    create sequence artprice_seq start with 1000;
    create table artprice(
        id          int primary key default nextval('artprice_seq'),
        code        varchar(20) unique, -- not null?
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.Ver

        art         int not null
            references art(id) on update cascade on delete cascade,

        buy         decimal(12, 2),
        sell        decimal(12, 2)
    );

    create index artprice_lastmod     on artprice(lastmod desc);
    create index artprice_uid_acl     on artprice(uid, acl);
