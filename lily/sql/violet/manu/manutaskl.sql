--\import violet.art.artmodel
--\import violet.manu.manutask

    create sequence manutaskl_seq start with 1000;
    create table manutaskl(
        id          bigint primary key default nextval('manutaskl_seq'),
--\mixin lily.mixin.ExVer
        -- priority: index
--\mixin lily.mixin.Mi
        
        task        bigint not null
            references manutask(id) on update cascade on delete cascade,

        deadline    timestamp with time zone not null,
        status      varchar(100),
        
        model       int not null
            references artmodel(id) on update cascade,
        qty         numeric(20, 2) not null,
--\mixin violet.store._batch
        -- see also manutask_sn*
        
        ntrack      int         -- cache
    );

    create index manutaskl_lastmod     on manutaskl(lastmod desc);
