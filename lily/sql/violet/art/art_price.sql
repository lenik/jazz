--\import lily.account

    create sequence art_price_seq start with 1000;
    create table art_price(
        id          int primary key default nextval('art_price_seq'),
        code        varchar(20) unique, -- not null?
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.Ver

        art         int not null
            references art(id) on update cascade on delete cascade,

        buy         decimal(12, 2),
        sell        decimal(12, 2)
    );

    create index art_price_lastmod     on art_price(lastmod desc);
    create index art_price_uid_acl     on art_price(uid, acl);
