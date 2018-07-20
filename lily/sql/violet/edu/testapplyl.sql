--\import lily.account
--\import violet.edu.testq
--\import violet.edu.testapply

    create sequence testapplyl_seq start with 1000;
    create table testapplyl(
        id          bigint primary key default nextval('testapplyl_seq'),
--\mixin lily.mixin.ExVer

        apply       bigint not null
            references testapply(id) on update cascade,
        
        q           bigint not null
            references testq(id) on update cascade,
        
        ans         int, -- TODO ans[]?
        anstext     varchar(200),
        score       double precision,
        
        -- reserved.
        waittime    float
    );

    create index testapplyl_lastmod     on testapplyl(lastmod desc);
