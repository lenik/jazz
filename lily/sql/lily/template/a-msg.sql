--\import lily.inc.acl

    create sequence $1_msg_seq start with 1000;

    create table $1_msg(
        id          bigint primary key default nextval('$1_msg_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Mi
--\mixin lily.mixin.Msg
--\mixin lily.mixin.PropFiles
--\mixin lily.mixin.FavLike

        "$1"        ${2=int} not null
            references "$1" on update cascade on delete cascade,
        
        parent      bigint
            references $1_msg on update cascade
    );

    create index $1_msg_lastmod  on $1_msg(lastmod desc);
    create index $1_msg_state    on $1_msg(state);

    create or replace view v_$1_msg as
        select
            a.*,
            u.label uid_label,
            o.${3=label} obj_${3=label},
            parent.label parent_label,
            parent.description parent_description,
            parent.subject parent_subject
        from $1_msg a
            left join "$1" o on a."$1"=o.id
            left join "user" u on a.uid=u.id
            left join $1_msg parent on a.parent=parent.id
            ;

