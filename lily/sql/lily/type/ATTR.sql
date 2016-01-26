--\dnl Try mixin Props if you don't have to index the attributes.

    create sequence $1_attr_seq start with 1000;

    create table $1_attr(
        id          int primary key default nextval('$1_attr_seq'),
--\mixin lily.mixin.Ver

        obj         int not null
            references "$1"(id) on update cascade on delete cascade,

        attr        int not null
            references attr(id) on update cascade,

        ival        int,
        sval        varchar(200)
    );

    create index $1_attr_ival       on $1_attr(ival);
    create index $1_attr_sval       on $1_attr(sval);

    create or replace view v_$1_attr as
        select
            a.*,
            o.${2=label} obj_${2=label},
            attr.label attr_label
        from $1_attr a
            left join "$1" o on a.obj=o.id
            left join attr on a.attr=attr.id
            ;
