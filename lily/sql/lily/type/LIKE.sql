--\import lily.account.user

    create sequence $1_like_seq start with 1000;

    create table $1_like(
        id          bigint primary key default nextval('$1_like_seq'),

        obj         int not null
            references "$1"(id) on update cascade on delete cascade,

        "user"      int not null
            references "user"(id) on update cascade on delete cascade,

        --  1 for like
        -- -1 for hate
        score       int not null default 1
    );

    create or replace view v_$1_like as
        select
            o.*,
            u.id        user_id,
            u.label     user_label
        from $1_like a
            left join "$1" o    on a.obj = o.id
            left join "user" u  on a."user" = u.id
            ;
