--\dnl Try mixin Props if you don't have to index the tags.

--\import lily.inc.acl

    create sequence $1_tag_seq start with 1000;

    create table $1_tag(
        id          int primary key default nextval('$1_tag_seq'),
--\mixin lily.mixin.Ver

        obj         int not null
            references "$1"(id) on update cascade on delete cascade,

        tag         int not null
            references _tag(id) on update cascade

        -- constraint uk_obj_tag unique(obj, tag)
    );

    create or replace view v_$1_tag as
        select
            a.*,
            o.${2=label} obj_${2=label},
            _tag.label tag_label
        from $1_tag a
            left join "$1" o on a.obj = o.id
            left join _tag   on a.tag = _tag.id
            ;
