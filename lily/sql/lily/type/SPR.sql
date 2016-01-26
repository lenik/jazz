--\import lily.inc.acl
--\import lily.press.sprsite

    create sequence $1_spr_seq start with 1000;

    create table $1_spr(
        id          bigint primary key default nextval('$1_spr_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer

        obj         int not null
            references "$1"(id) on update cascade on delete cascade,

        site        int not null
            references sprsite(id) on update cascade on delete cascade,
        key         varchar(30),

        score       int not null default 0
    );

    create or replace view v_$1_spr as
        select
            a.*,
            o.${2=label} obj_${2=label},
            u.label user_label,
            s.label site_label
        from $1_spr a
            left join "$1" o on a.obj=o.id
            left join "user" u on a.uid=u.id
            left join sprsite s on a.site=s.id
            ;
