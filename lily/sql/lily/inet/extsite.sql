--\import lily.account

    create sequence extsite_seq start with 1000;
    create table extsite(
        id          int primary key default nextval('extsite_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Props

        parent      int
            references extsite(id) on update cascade on delete cascade,

        depth       int not null default -1,

        -- http://.../...%s...
        urlfmt      varchar(200),
        
        bonus       int not null default 0,

        -- @Redundant
        --  select count(*) from post where post.cat=cat.id
        count       int not null default 0
    );

    create index extsite_label       on extsite(label);
    create index extsite_lastmod     on extsite(lastmod desc);
    create index extsite_priority    on extsite(priority);
    create index extsite_state       on extsite(state);
    create index extsite_uid_acl     on extsite(uid, acl);
