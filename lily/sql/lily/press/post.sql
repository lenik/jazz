--\import lily.inc.msg

    create sequence post_seq start with 1000;

    create table post(
        id          int primary key default nextval('post_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Mi
--\mixin lily.mixin.Msg

        parent      int
            references post(id) on update cascade on delete set null,

        orig        int
            references post(id) on update cascade on delete set null,

        cat         int
            references postcat(id) on update cascade,

--\mixin lily.mixin.FavLike

        -- count from post_re
        nmsg        int not null default 0,

        plugins     jsonb
    );

    create index post_lastmod        on post(lastmod desc);
    create index post_priority_nvote on post(priority, nvote desc);
    create index post_state          on post(state);
    create index post_subject        on post(subject);
    create index post_t0t1           on post(t0, t1);
    create index post_t1             on post(t1);
    create index post_uid_acl        on post(uid, acl);
