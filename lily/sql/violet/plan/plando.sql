--\import lily.contact.org
--\import lily.contact.person
--\import violet.plan.plan

    create sequence plando_seq;
    create table plando(
        id          bigint primary key default nextval('plando_seq'),

--\mixin lily.mixin.Acl_rw-r-----
--\mixin lily.mixin.Ex
--\mixin lily.mixin.Mi
--\mixin lily.mixin.Msg
--\mixin lily.mixin.Ver

        nvote       int not null default 0,

        plan       bigint not null
            references plan(id) on update cascade,

        parent      int
            references plando(id) on update cascade,

        changes     text[]
    );

    create index plando_lastmod      on plando(lastmod desc);
    create index plando_priority     on plando(priority);
    create index plando_state        on plando(state);
    create index plando_t0t1         on plando(t0, t1);
    create index plando_t1           on plando(t1);
    create index plando_uid_acl      on plando(uid, acl);

-- drop table if exists plando_party;
    create sequence plando_party_seq;
    create table plando_party(
        id          int primary key default nextval('plando_party_seq'),
        plando       bigint not null
            references plando(id) on update cascade on delete cascade,

        person      int
            references person(id) on update cascade on delete cascade,

        org         int
            references org(id) on update cascade on delete cascade,

        description varchar(60)
    );

--\mixin lily.template.a-tag plando
--\mixin lily.template.a-tags plando bigint subject
--\mixin lily.template.a-parm plando
--\mixin lily.template.a-parms plando bigint subject
--\mixin lily.template.a-votes plando bigint subject
