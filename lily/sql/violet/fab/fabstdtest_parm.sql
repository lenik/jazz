--\import lily.account
--\import violet.art.art
--\import violet.fab.fabproc

    create sequence fabstdtest_parm_seq start with 1000;
    create table fabstdtest_parm(
        id          int primary key default nextval('fabstdtest_parm_seq'),
--\mixin lily.mixin.Ver
        
        test        int not null
            references fabstdtest(id) on update cascade on delete cascade,
        required    boolean not null default false,
        
--\mixin lily.mixin.Props
        -- expected: variant
        -- format: number format if expected is numeric.
        -- range: { min, max }: variants
        
        -- variant string.
        expected    varchar(100)
    );

    create index fabstdtest_parm_lastmod     on fabstdtest_parm(lastmod desc);
