--\import lily.contact.org
--\import lily.contact.person
--\import violet.plan.plan

    create sequence plog_seq;
    create table plog(
        id          bigint primary key default nextval('plog_seq'),

--\mixin lily.mixin.Acl_rw-r-----
--\mixin lily.mixin.Ex
--\mixin lily.mixin.Mi
--\mixin lily.mixin.Msg
--\mixin lily.mixin.Ver

        nvote       int not null default 0,

        plan       bigint not null
            references plan(id) on update cascade on delete set null,

        parent      int
            references plog(id) on update cascade on delete set null,

        changes     text[]
    );

    create index plog_lastmod      on plog(lastmod desc);
    create index plog_priority     on plog(priority);
    create index plog_state        on plog(state);
    create index plog_t0t1         on plog(t0, t1);
    create index plog_t1           on plog(t1);
    create index plog_uid_acl      on plog(uid, acl);

-- drop table if exists plog_party;
    create sequence plog_party_seq;
    create table plog_party(
        id          int primary key default nextval('plog_party_seq'),
        plog       bigint not null
            references plog(id) on update cascade on delete cascade,

        person      int
            references person(id) on update cascade on delete cascade,

        org         int
            references org(id) on update cascade on delete cascade,

        description varchar(60)
    );

--\mixin lily.template.a-tag plog
--\mixin lily.template.a-tags plog bigint subject
--\mixin lily.template.a-parm plog
--\mixin lily.template.a-parms plog bigint subject
--\mixin lily.template.a-votes plog bigint subject
