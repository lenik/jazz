--\import lily.contact.person
--\import violet.manu.manutrack

    create sequence manutrack_op_seq start with 1000;
    create table manutrack_op(
        id          bigint primary key default nextval('manutrack_op_seq'),
--\mixin lily.mixin.Ver
        track       bigint not null
            references manutrack(id) on update cascade on delete cascade,
        person      int not null
            references person(id) on update cascade on delete cascade,
        role        varchar(30)
    );

    create index manutrack_op_lastmod     on manutrack_op(lastmod desc);
