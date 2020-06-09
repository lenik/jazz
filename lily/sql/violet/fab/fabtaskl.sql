--\import violet.art.artmodel
--\import violet.fab.fabtask

    create sequence fabtaskl_seq start with 1000;
    create table fabtaskl(
        id          bigint primary key default nextval('fabtaskl_seq'),
--\mixin lily.mixin.ExVer
        -- priority: index
--\mixin lily.mixin.Mi
        
        task        bigint not null
            references fabtask(id) on update cascade on delete cascade,

        deadline    timestamp with time zone not null,
        status      varchar(100),
        
        model       int not null
            references artmodel(id) on update cascade,
        qty         numeric(20, 2) not null,
--\mixin violet.store._batch
        -- see also fabtask_sn*
        
        ntrack      int         -- cache
    );

    create index fabtaskl_lastmod     on fabtaskl(lastmod desc);
