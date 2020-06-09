--\import violet.fab.fabstdtest
--\import violet.fab.fabtrack

    create sequence fabtrack_test_seq start with 1000;
    create table fabtrack_test(
        id          bigint primary key default nextval('fabtrack_test_seq'),
--\mixin lily.mixin.Ver
        
        track       bigint not null
            references fabtrack(id) on update cascade on delete cascade,
        
        -- std := track.parent -> proc -> stdproc.test
        std         int not null
            references fabstdtest(id) on update cascade,
        
        valid       boolean not null
    );

    create index fabtrack_test_lastmod      on fabtrack_test(lastmod desc);
