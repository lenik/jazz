--\import lily.account.user
--\import lily.store.art

    create sequence art_like_seq start with 1000;

    create table art_like(
        id          int primary key default nextval('art_like_seq'),

        art         int not null
            references art(id) on update cascade on delete cascade,

        "user"      int not null
            references "user"(id) on update cascade on delete cascade
    );

    create or replace view v_art_like as
        select
            a.art       art_id,
            art.label   art_label,
            a."user"    user_id,
            u.label     user_label
        from art_like a
            left join art on a.art=art.id
            left join "user" u on a."user"=u.id;
