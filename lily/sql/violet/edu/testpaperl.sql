--\import lily.account
--\import violet.edu.testq
--\import violet.edu.testpaper

    create sequence testpaperl_seq start with 1000;
    create table testpaperl(
        id          bigint primary key default nextval('testpaperl_seq'),
--\mixin lily.mixin.ExVer
--\mixin lily.mixin.Props

        paper       int not null
            references testpaper(id) on update cascade,
        
        q           bigint not null
            references testq(id) on update cascade,
        
        score       numeric(6, 2)
    );

    create index testpaperl_lastmod     on testpaperl(lastmod desc);
