--\import lily.account

    create sequence sprsite_seq start with 1000;
    create table sprsite(
        id          int primary key default nextval('sprsite_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Props

        parent      int
            references sprsite(id) on update cascade on delete cascade,

        depth       int not null default -1,

        -- http://.../...%s...
        urlfmt      varchar(200),
        
        bonus       int not null default 0,

        -- @Redundant
        --  select count(*) from post where post.cat=cat.id
        count       int not null default 0
    );

    create index sprsite_label       on sprsite(label);
    create index sprsite_lastmod     on sprsite(lastmod desc);
    create index sprsite_priority    on sprsite(priority);
    create index sprsite_state       on sprsite(state);
    create index sprsite_uid_acl     on sprsite(uid, acl);
