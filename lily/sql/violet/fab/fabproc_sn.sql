--\import violet.fab.fabproc

    create sequence fabproc_sn_seq start with 1000;
    create table fabproc_sn(
        id          bigint primary key default nextval('fabproc_sn_seq'),
--\mixin lily.mixin.Ver

        proc        bigint not null
            references fabproc(id) on update cascade on delete cascade,
        serial      varchar(40) not null
    );

    create index fabproc_sn_lastmod     on fabproc_sn(lastmod desc);
