--\import violet.art.art
--\import violet.fab.fabodr

    create sequence fabodrl_seq start with 1000;
    create table fabodrl(
        id          bigint primary key default nextval('fabodrl_seq'),
--\mixin lily.mixin.ExVer
--\mixin lily.mixin.Mi
--\mixin lily.mixin.Props
            -- t0: contract date, t1: deadline

        odr         bigint not null
            references fabodr(id) on update cascade on delete cascade,

        art         int not null
            references art(id) on update cascade,

        resale      boolean not null default false,
        o_label     varchar(30),    -- label override
        o_spec      varchar(80),    -- spec override
        o_uom       varchar(30),    -- uom override
        
        qty         numeric(20,2) not null,
        price       numeric(20,2) not null default 0,
        amount      numeric(20,2) not null default 0,   -- cache
        
        notes       varchar(200)
    );
