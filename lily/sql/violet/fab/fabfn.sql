--\import lily.account

    create sequence fabfn_seq start with 1000;
    create table fabfn(
        id          int primary key default nextval('fabfn_seq'),
        code        varchar(20) unique, -- not null?
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
        
        image       varchar(200),
        imagealt    varchar(80),

--\mixin lily.mixin.Props
    -- bonus

        parent      int
            references fabfn(id) on update cascade on delete cascade,

        depth       int not null default -1,

        -- @Redundant
        --  select count(*) from post where post.cat=cat.id
        nref        int not null default 0
    );

    create index fabfn_label        on fabfn(label);
    create index fabfn_lastmod      on fabfn(lastmod desc);
    create index fabfn_priority     on fabfn(priority);
    create index fabfn_state        on fabfn(state);
    create index fabfn_uid_acl      on fabfn(uid, acl);
