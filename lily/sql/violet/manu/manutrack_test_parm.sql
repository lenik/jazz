--\import violet.manu.manustdtest_parm
--\import violet.manu.manutrack_test

    create sequence manutrack_test_parm_seq start with 1000;
    create table manutrack_test_parm(
        id          bigint primary key default nextval('manutrack_test_parm_seq'),
--\mixin lily.mixin.Ver
        
        test        bigint not null
            references manutrack_test(id) on update cascade on delete cascade,
        
        parm        int not null
            references manustdtest_parm(id) on update cascade,
        
        -- automated := tester is not null
        tester      int
            references manustdtester(id) on update cascade on delete set null,

        actual      varchar(100) not null,
        valid       boolean not null
    );

    create index manutrack_test_parm_lastmod     on manutrack_test_parm(lastmod desc);
