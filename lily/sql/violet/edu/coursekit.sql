--\import lily.account
--\mixin lily.template.a-cat coursekit
    
    -- 课件
    create sequence coursekit_seq start with 1000;
    create table coursekit(
        id          int primary key default nextval('coursekit_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer

        cat         int not null
            references coursekitcat(id) on update cascade,
        
        course      int not null
            references course(id) on update cascade,

        -- {
        --   duration: estimated minutes to take to learn.
        -- }
--\mixin lily.mixin.Props
--\mixin lily.mixin.FavLike

        dummy       jsonb
    );

    create index coursekit_lastmod         on coursekit(lastmod desc);
    create index coursekit_priority        on coursekit(priority desc);
    create index coursekit_state           on coursekit(state);
    create index coursekit_uid_acl         on coursekit(uid, acl);

--\mixin lily.template.a-tag coursekit
--\mixin lily.template.a-tags coursekit int label
--\mixin lily.template.a-favs coursekit int label
