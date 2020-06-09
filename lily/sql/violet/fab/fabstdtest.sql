--\import lily.account
--* mixin lily.template.a-cat * fabstdtest

    create sequence fabstdtest_seq start with 1000;
    create table fabstdtest(
        id          int primary key default nextval('fabstdtest_seq'),
        code        varchar(20) unique, -- not null?
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
        
        image       varchar(200),
        imagealt    varchar(80),

--\mixin lily.mixin.Props
    -- bonus

        cat         int not null
            references fabstdtestcat(id) on update cascade,
        
        parent      int
            references fabstdtest(id) on update cascade on delete cascade,

        depth       int not null default -1,

        -- @Redundant
        --  select count(*) from post where post.cat=cat.id
        nref        int not null default 0
    );

    create index fabstdtest_label        on fabstdtest(label);
    create index fabstdtest_lastmod      on fabstdtest(lastmod desc);
    create index fabstdtest_priority     on fabstdtest(priority);
    create index fabstdtest_state        on fabstdtest(state);
    create index fabstdtest_uid_acl      on fabstdtest(uid, acl);
