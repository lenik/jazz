--\import lily.account.user
--\import lily.press.post

    create table post_like(
        post        int not null
            references post(id) on update cascade on delete cascade,

        "user"      int not null
            references "user"(id) on update cascade on delete cascade
    );

    create or replace view v_post_like as
        select
            i.id        post_id,
            i.label     post_label,
            u.id        user_id,
            u.label     user_label
        from post_like a
            left join post i on a.post=i.id
            left join "user" u on a."user"=u.id;
