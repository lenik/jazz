--\import lily.inc.acl

    create sequence $1_re_seq start with 1000;

    create table $1_re(
        id          int primary key default nextval('$1_re_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.FavLike

        "$1"        ${2=int} not null
            references "$1" on update cascade on delete cascade
    );

    create index $1_re_lastmod  on $1_re(lastmod desc);
    create index $1_re_state    on $1_re(state);

    create or replace view v_$1_re as
        select
            a.*,
            u.label uid_label,
            o.${3=label} obj_${3=label}
        from $1_re a
            left join "$1" o on a."$1"=o.id
            left join "user" u on a.uid=u.id
            ;
