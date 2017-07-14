--\import lily.inc.acl
--\import lily.inet.extsite

    create sequence $1_backref_seq start with 1000;

    create table $1_backref(
        id          bigint primary key default nextval('$1_backref_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer

        "$1"        int not null
            references "$1"(id) on update cascade on delete cascade,

        site        int not null
            references extsite(id) on update cascade on delete cascade,
        key         varchar(30),

        -- @Redundant
        value       int not null default 0
    );

    create or replace view v_$1_backref as
        select
            a.*,
            o.${2=label} obj_${2=label},
            u.label user_label,
            s.label site_label
        from $1_backref a
            left join "$1" o on a."$1"=o.id
            left join "user" u on a.uid=u.id
            left join extsite s on a.site=s.id
            ;
