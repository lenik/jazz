--\import violet.art.art
--\import violet.store.region

    create sequence art_region_seq start with 1000;
    create table art_region(
        id          int primary key default nextval('art_region_seq'),
--\mixin lily.mixin.ExVer
        -- state: LOCK

        art         int not null
            references art(id) on update cascade on delete cascade,

        region      int not null
            references region(id) on update cascade on delete cascade,

        qty_min     numeric(20, 2) not null default 0,
        qty_max     numeric(20, 2) not null default 0,
        checkperiod int not null default 365,
        checkexpire timestamp with time zone not null default now() + 365 * interval '86400',

        description varchar(100),

        unique(art, region)
    );
