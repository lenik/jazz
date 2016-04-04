--\dnl Try mixin Props if you don't have to index the parmeters.

    create sequence $1_parm_seq start with 1000;

    create table $1_parm(
        id          int primary key default nextval('$1_parm_seq'),
--\mixin lily.mixin.Ver

        obj         int not null
            references "$1"(id) on update cascade on delete cascade,

        parm        int not null
            references parm(id) on update cascade,

        ival        int,
        sval        varchar(200)
    );

    create index $1_parm_ival       on $1_parm(ival);
    create index $1_parm_sval       on $1_parm(sval);

    create or replace view v_$1_parm as
        select
            a.*,
            o.${2=label} obj_${2=label},
            parm.label parm_label
        from $1_parm a
            left join "$1" o on a.obj=o.id
            left join parm on a.parm=parm.id
            ;

