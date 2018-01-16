--\import violet.manu.manustdtest
--\import violet.manu.manutrack

    create sequence manutrack_test_seq start with 1000;
    create table manutrack_test(
        id          bigint primary key default nextval('manutrack_test_seq'),
--\mixin lily.mixin.Ver
        
        track       bigint not null
            references manutrack(id) on update cascade on delete cascade,
        
        -- std := track.parent -> proc -> stdproc.test
        std         int not null
            references manustdtest(id) on update cascade,
        
        valid       boolean not null
    );

    create index manutrack_test_lastmod      on manutrack_test(lastmod desc);
