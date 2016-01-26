--\import lily.account

    create sequence postcat_seq start with 1000;
    create table postcat(
        id          int primary key default nextval('postcat_seq'),
        code        varchar(20) unique, -- not null?
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Props
    -- bonus

        parent      int
            references postcat(id) on update cascade on delete cascade,

        depth       int not null default -1,

        -- @Redundant
        --  select count(*) from post where post.cat=cat.id
        nref        int not null default 0
    );

    create index postcat_label       on postcat(label);
    create index postcat_lastmod     on postcat(lastmod desc);
    create index postcat_priority    on postcat(priority);
    create index postcat_state       on postcat(state);
    create index postcat_uid_acl     on postcat(uid, acl);
