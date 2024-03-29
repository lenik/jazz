--\import lily.inc.msg
--\import lily.pub.postcat

    create sequence post_seq start with 1000;

    create table post(
        id          bigint primary key default nextval('post_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.ExVer
--\mixin lily.mixin.Mi
--\mixin lily.mixin.Msg

        parent      bigint
            references post on update cascade on delete set null,

        cat         int
            references postcat on update cascade,

    -- props:
        -- seealso.orig: for template
        -- seealso.quote: forward/quote.

--\mixin lily.mixin.FavLike

        -- count from reply
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

--\mixin lily.template.a-tag post
--\mixin lily.template.a-tags post bigint subject
--\mixin lily.template.a-parm post
--\mixin lily.template.a-parms post bigint subject
--\mixin lily.template.a-backrefs post bigint subject
--\mixin lily.template.a-favs post bigint subject
--\mixin lily.template.a-votes post bigint subject
