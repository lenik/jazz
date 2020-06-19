--\import lily.account

    create sequence $1cat_seq start with 1000;
    create table $1cat(
        id          int primary key default nextval('$1cat_seq'),
        code        varchar(20) unique, -- not null?
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
        
        image       varchar(200),
        imagealt    varchar(80),

--\mixin lily.mixin.Props
    -- bonus

        parent      int
            references $1cat on update cascade on delete cascade,

        depth       int not null default -1,

        -- @Redundant
        --  select count(*) from post where post.cat=cat.id
        nref        int not null default 0
    );

    create index $1cat_label        on $1cat(label);
    create index $1cat_lastmod      on $1cat(lastmod desc);
    create index $1cat_priority     on $1cat(priority);
    create index $1cat_state        on $1cat(state);
    create index $1cat_uid_acl      on $1cat(uid, acl);
