--\import lily.account
--\import violet.art.art
--\import violet.manu.manuproc

    create sequence manustdtest_parm_seq start with 1000;
    create table manustdtest_parm(
        id          int primary key default nextval('manustdtest_parm_seq'),
--\mixin lily.mixin.Ver
        
        std         int not null
            references manustdtest(id) on update cascade on delete cascade,
        required    boolean not null default false,
        
--\mixin lily.mixin.Props
        -- expected: variant
        -- format: number format if expected is numeric.
        -- range: { min, max }: variants
        
        -- variant string.
        expected    varchar(100)
    );

    create index manustdtest_parm_lastmod     on manustdtest_parm(lastmod desc);
