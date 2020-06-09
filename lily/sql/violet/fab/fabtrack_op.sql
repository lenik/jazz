--\import lily.contact.person
--\import violet.fab.fabtrack

    create sequence fabtrack_op_seq start with 1000;
    create table fabtrack_op(
        id          bigint primary key default nextval('fabtrack_op_seq'),
--\mixin lily.mixin.Ver
        track       bigint not null
            references fabtrack(id) on update cascade on delete cascade,
        person      int not null
            references person(id) on update cascade on delete cascade,
        role        varchar(30)
    );

    create index fabtrack_op_lastmod     on fabtrack_op(lastmod desc);
