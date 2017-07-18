--\import lily.contact.org
--\import lily.contact.person
--\import violet.issue.issue

    create sequence issuea_seq;
    create table issuea(
        id          int primary key default nextval('issuea_seq'),

--\mixin lily.mixin.Acl_rw-r-----
--\mixin lily.mixin.Ex
--\mixin lily.mixin.Mi
--\mixin lily.mixin.Msg
--\mixin lily.mixin.Ver

        nvote       int not null default 0,

        issue       int not null
            references issue(id) on update cascade on delete set null,

        parent      int
            references issuea(id) on update cascade on delete set null,

        changes     text[]
    );

    create index issuea_lastmod      on issuea(lastmod desc);
    create index issuea_priority     on issuea(priority);
    create index issuea_state        on issuea(state);
    create index issuea_t0t1         on issuea(t0, t1);
    create index issuea_t1           on issuea(t1);
    create index issuea_uid_acl      on issuea(uid, acl);

--\mixin lily.template.a-votes issuea

