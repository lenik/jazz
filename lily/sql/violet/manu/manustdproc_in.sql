--\import lily.account
--\import violet.art.art
--\import violet.manu.manustdproc

    create sequence manustdproc_in_seq start with 1000;
    create table manustdproc_in(
        id          int primary key default nextval('manustdproc_in_seq'),
--\mixin lily.mixin.Ver
        proc        int not null
            references manustdproc(id) on update cascade on delete cascade,
        
        -- either model or art must be set.
        model       int
            references artmodel(id) on update cascade,
        
        -- material without model (eg. raw material)
        art         int
            references art(id) on update cascade,
        
        qty         numeric(20, 2) not null default 1
    );

    create index manustdproc_in_lastmod     on manustdproc_in(lastmod desc);
