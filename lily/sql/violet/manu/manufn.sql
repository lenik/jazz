--\import lily.account

    create sequence manufn_seq start with 1000;
    create table manufn(
        id          int primary key default nextval('manufn_seq'),
        code        varchar(20) unique, -- not null?
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
        
        image       varchar(200),
        imagealt    varchar(80),

--\mixin lily.mixin.Props
    -- bonus

        parent      int
            references manufn(id) on update cascade on delete cascade,

        depth       int not null default -1,

        -- @Redundant
        --  select count(*) from post where post.cat=cat.id
        nref        int not null default 0
    );

    create index manufn_label        on manufn(label);
    create index manufn_lastmod      on manufn(lastmod desc);
    create index manufn_priority     on manufn(priority);
    create index manufn_state        on manufn(state);
    create index manufn_uid_acl      on manufn(uid, acl);
