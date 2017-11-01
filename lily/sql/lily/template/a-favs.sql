--\import lily.account.user

    create sequence $1_fav_seq start with 1000;

    create table $1_fav(
        id          bigint primary key default nextval('$1_fav_seq'),

        "$1"         ${2=int} not null
            references "$1"(id) on update cascade on delete cascade,

        "user"      int not null
            references "user"(id) on update cascade on delete cascade
    );

    create or replace view v_$1_fav as
        select
            o.*,
            u.id        user_id,
            u.label     user_label
        from $1_fav a
            left join "$1" o on a."$1"=o.id
            left join "user" u on a."user"=u.id
            ;
