--\dnl Try mixin Props if you don't have to index the tags.

--\import lily.inc.acl

    create sequence $1_tag_seq start with 1000;

    create table $1_tag(
        id          int primary key default nextval('$1_tag_seq'),
--\mixin lily.mixin.Ver

        "$1"        ${2=int} not null
            references "$1" on update cascade on delete cascade,

        tag         int not null
            references $1tag on update cascade on delete cascade

        -- , constraint $1_tag_uk unique("$1", tag)
    );

    create or replace view v_$1_tag as
        select
            a.*,
            o.${3=label} obj_${3=label},
            tag.label tag_label
        from $1_tag a
            left join "$1" o on a."$1" = o.id
            left join $1tag tag on a.tag = tag.id
            ;
