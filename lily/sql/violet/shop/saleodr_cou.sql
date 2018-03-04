--\import violet.art.art
--\import violet.shop.saleodr
--\import violet.shop.coupon

    create sequence saleodr_cou_seq start with 1000;
    create table saleodr_cou(
        id          bigint primary key default nextval('saleodr_cou_seq'),
--\mixin lily.mixin.Ex

        odr         bigint not null
            references saleodr(id) on update cascade on delete cascade,

        coupon      bigint
            references coupon(id) on update cascade on delete set null
    );
