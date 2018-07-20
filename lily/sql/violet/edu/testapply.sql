--\import lily.account
--\import lily.contact.person
--\import violet.edu.testpaper

    create sequence testapply_seq start with 1000;
    create table testapply(
        id          bigint primary key default nextval('testapply_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Mi

        paper        int
            references testpaper(id) on update cascade,
        
        -- reserved..., use owner-user for now.
        person      int
            references person(id) on update cascade,
        
        -- (cache)
        score       numeric(10, 2)
    );

    create index testapply_lastmod      on testapply(lastmod desc);
    create index testapply_priority     on testapply(priority);
