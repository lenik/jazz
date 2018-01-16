--\import lily.account
--* mixin lily.template.a-cat * manustdtest

    create sequence manustdtest_seq start with 1000;
    create table manustdtest(
        id          int primary key default nextval('manustdtest_seq'),
        code        varchar(20) unique, -- not null?
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
        
        image       varchar(200),
        imagealt    varchar(80),

--\mixin lily.mixin.Props
    -- bonus

        parent      int
            references manustdtest(id) on update cascade on delete cascade,

        depth       int not null default -1,

        -- @Redundant
        --  select count(*) from post where post.cat=cat.id
        nref        int not null default 0
    );

    create index manustdtest_label        on manustdtest(label);
    create index manustdtest_lastmod      on manustdtest(lastmod desc);
    create index manustdtest_priority     on manustdtest(priority);
    create index manustdtest_state        on manustdtest(state);
    create index manustdtest_uid_acl      on manustdtest(uid, acl);
