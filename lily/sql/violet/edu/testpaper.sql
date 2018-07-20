--\import lily.account
--\import lily.contact.person
--\import violet.edu.course

    -- 考试卷（固定格式）
    
    create sequence testpaper_seq start with 1000;
    create table testpaper(
        id          int primary key default nextval('testpaper_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer

        course      int not null
            references course(id) on update cascade,
        
        -- in minutes
        timeout     int not null default 60,
        
        -- (cache)
        totalscore  int not null default 100
    );

    create index testpaper_lastmod      on testpaper(lastmod desc);
    create index testpaper_priority     on testpaper(priority);
