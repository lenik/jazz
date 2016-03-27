--\import lily.store.art
--\import lily.store.room

-- drop table if exists artroomopt;
    create table artroomopt(
        --\mixin lily.mixin.ExVer
        -- state:
        --     locked

        art         int not null
            references art(id) on update cascade on delete cascade,

        room        int not null
            references room(id) on update cascade on delete cascade,

        qty_min     numeric(20, 2) not null default 0,
        qty_max     numeric(20, 2) not null default 0,
        checkperiod int not null default 365,
        checkexpire timestamp not null default now() + 365 * interval '86400',

        description varchar(100),

        constraint artroomopt_uk unique(art, room)
    );