--\import lily.account
--\import violet.art.art
--\import violet.fab.fabstdproc

    create sequence fabstdproc_in_seq start with 1000;
    create table fabstdproc_in(
        id          int primary key default nextval('fabstdproc_in_seq'),
--\mixin lily.mixin.Ver

        proc        int not null
            references fabstdproc(id) on update cascade on delete cascade,
        
        -- either model or art must be set.
        model       int
            references artmodel(id) on update cascade,
        
        -- material without model (eg. raw material)
        art         int
            references art(id) on update cascade,
        
        qty         numeric(20, 2) not null default 1
    );

    create index fabstdproc_in_lastmod     on fabstdproc_in(lastmod desc);
