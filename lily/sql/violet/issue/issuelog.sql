--\import lily.contact.org
--\import lily.contact.person
--\import violet.issue.issue

    create sequence issuelog_seq;
    create table issuelog(
        id          bigint primary key default nextval('issuelog_seq'),

--\mixin lily.mixin.Acl_rw-r-----
--\mixin lily.mixin.Ex
--\mixin lily.mixin.Mi
--\mixin lily.mixin.Msg
--\mixin lily.mixin.Ver

        nvote       int not null default 0,

        issue       bigint not null
            references issue(id) on update cascade on delete set null,

        parent      bigint
            references issuelog(id) on update cascade on delete set null,

        changes     text[]
    );

    create index issuelog_lastmod      on issuelog(lastmod desc);
    create index issuelog_priority     on issuelog(priority);
    create index issuelog_state        on issuelog(state);
    create index issuelog_t0t1         on issuelog(t0, t1);
    create index issuelog_t1           on issuelog(t1);
    create index issuelog_uid_acl      on issuelog(uid, acl);

--\mixin lily.template.a-votes issuelog bigint subject
