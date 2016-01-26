--\import lily.account.user
--\import lily.press.sprsite
--\import lily.store.art

-- spread, promote, propgation, popularize
    create sequence art_spr_seq start with 1000;

    create table art_spr(
        id          bigint primary key default nextval('art_spr_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer

        art         int not null
            references art(id) on update cascade on delete cascade,

        site        int not null
            references sprsite(id) on update cascade on delete cascade,
        key         varchar(30),

        -- @Redundant
        bonus       int not null default 0
    );

    create or replace view v_art_spr as
        select
            a.*,
            art.label   art_label,
            u.label     user_label,
            s.label     site_label
        from art_spr a
            left join art on a.art=art.id
            left join "user" u on a."user"=u.id
            left join sprsite s on a.site=s.id
            ;
