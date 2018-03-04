--\import lily.account
--\import violet.art.art
--\mixin lily.template.a-cat coupon

    create sequence coupon_seq start with 1000;
    create table coupon(
        id          bigint primary key default nextval('coupon_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.Ex
--\mixin lily.mixin.Ver

        cat         int not null
            references couponcat(id) on update cascade on delete cascade,

        "user"      int
            references "user"(id) on update cascade on delete cascade
    );

    create index coupon_lastmod     on coupon(lastmod desc);
    create index coupon_uid_acl     on coupon(uid, acl);
