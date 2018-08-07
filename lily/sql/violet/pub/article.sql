--\import lily.inc.msg
--\mixin lily.pub.articlecat

    create sequence article_seq start with 1000;

    create table article(
        id          bigint primary key default nextval('article_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Mi
--\mixin lily.mixin.Msg

        cat         int
            references articlecat(id) on update cascade,

    -- props:
        -- seealso.orig: for template
        -- seealso.quote: forward/quote.

--\mixin lily.mixin.FavLike

        -- count from reply
        nmsg        int not null default 0,

        plugins     jsonb
    );

    create index article_lastmod        on article(lastmod desc);
    create index article_priority_nvote on article(priority, nvote desc);
    create index article_state          on article(state);
    create index article_subject        on article(subject);
    create index article_t0t1           on article(t0, t1);
    create index article_t1             on article(t1);
    create index article_uid_acl        on article(uid, acl);

--\mixin lily.template.a-tag article
--\mixin lily.template.a-tags article bigint subject
--\mixin lily.template.a-parm article
--\mixin lily.template.a-parms article bigint subject
--\mixin lily.template.a-backrefs article bigint subject
--\mixin lily.template.a-favs article bigint subject
--\mixin lily.template.a-votes article bigint subject
