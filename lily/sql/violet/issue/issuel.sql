--\import lily.contact.org
--\import lily.contact.person
--\import violet.issue.issue

    create sequence issuel_seq;
    create table issuel(
        id          bigint primary key default nextval('issuel_seq'),

--\mixin lily.mixin.Acl_rw-r-----
--\mixin lily.mixin.Ex
--\mixin lily.mixin.Mi
--\mixin lily.mixin.Msg
--\mixin lily.mixin.Ver

        nvote       int not null default 0,

        issue       bigint not null
            references issue(id) on update cascade,

        parent      bigint
            references issuel(id) on update cascade,

        changes     text[]
    );

    create index issuel_lastmod      on issuel(lastmod desc);
    create index issuel_priority     on issuel(priority);
    create index issuel_state        on issuel(state);
    create index issuel_t0t1         on issuel(t0, t1);
    create index issuel_t1           on issuel(t1);
    create index issuel_uid_acl      on issuel(uid, acl);

--\mixin lily.template.a-votes issuel bigint subject
