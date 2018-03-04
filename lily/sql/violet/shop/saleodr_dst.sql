--\import violet.art.art
--\import violet.shop.saleodr
--\import violet.shop.discount

    create sequence saleodr_dst_seq start with 1000;
    create table saleodr_dst(
        id          bigint primary key default nextval('saleodr_dst_seq'),
--\mixin lily.mixin.Ex
--\mixin lily.mixin.Mi

        odr         bigint not null
            references saleodr(id) on update cascade on delete cascade,

        -- null if no corresponding shop item.
        dst         int
            references discount(id) on update cascade on delete set null
    );
