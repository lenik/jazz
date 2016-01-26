--\import lily.account.user
--\import lily.press.post
--\import lily.press.sprsite

-- spread, promote, propgation, popularize
    create sequence post_spr_seq start with 1000;

    create table post_spr(
        id          bigint primary key default nextval('post_spr_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer

        post        int not null
            references post(id) on update cascade on delete cascade,

        site        int not null
            references sprsite(id) on update cascade on delete cascade,
        key         varchar(30),

        -- @Redundant
        bonus       int not null default 0
    );

    create or replace view v_post_spr as
        select
            a.*,
            post.label  post_label,
            u.label     user_label,
            s.label     site_label
        from post_spr a
            left join post on a.post=post.id
            left join "user" u on a.uid=u.id
            left join sprsite s on a.site=s.id
            ;
