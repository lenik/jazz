--\import violet.manu.manuproc

    create sequence manuproc_sn_seq start with 1000;
    create table manuproc_sn(
        id          bigint primary key default nextval('manuproc_sn_seq'),
--\mixin lily.mixin.Ver
        proc        bigint not null
            references manuproc(id) on update cascade on delete cascade,
        serial      varchar(40) not null
    );

    create index manuproc_sn_lastmod     on manuproc_sn(lastmod desc);
