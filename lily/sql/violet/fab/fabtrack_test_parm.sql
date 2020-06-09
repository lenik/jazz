--\import violet.fab.fabstdtest_parm
--\import violet.fab.fabtrack_test

    create sequence fabtrack_test_parm_seq start with 1000;
    create table fabtrack_test_parm(
        id          bigint primary key default nextval('fabtrack_test_parm_seq'),
--\mixin lily.mixin.Ver
        
        test        bigint not null
            references fabtrack_test(id) on update cascade on delete cascade,
        
        parm        int not null
            references fabstdtest_parm(id) on update cascade,
        
        -- automated := tester is not null
        tester      int
            references fabstdtester(id) on update cascade on delete set null,

        actual      varchar(100) not null,
        valid       boolean not null
    );

    create index fabtrack_test_parm_lastmod     on fabtrack_test_parm(lastmod desc);
