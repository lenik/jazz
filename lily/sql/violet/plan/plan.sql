--\import lily.inc.msg
--\import lily.contact.org
--\import lily.contact.person
--\mixin lily.template.a-cat plan
--\mixin lily.template.a-phase plan

    create sequence plan_seq start with 1000;
    create table plan(
        id          bigint primary key default nextval('plan_seq'),

--\mixin lily.mixin.Acl_rw-r-----
--\mixin lily.mixin.Ex
--\mixin lily.mixin.Mi
--\mixin lily.mixin.Msg
--\mixin lily.mixin.Ver

        cat         int not null
            references plancat(id) on update cascade,

        phase       int not null
            references planphase(id) on update cascade,

        nread       int not null default 0,
        nvote       int not null default 0,
        nlike       int not null default 0,

        value       double precision not null default 0 -- estimated
        -- valmax   double precision,
    );

    create index plan_lastmod        on plan(lastmod desc);
    create index plan_priority_nvote on plan(priority, nvote desc);
    create index plan_state          on plan(state);
    create index plan_subject        on plan(subject);
    create index plan_t0t1           on plan(t0, t1);
    create index plan_t1             on plan(t1);
    create index plan_uid_acl        on plan(uid, acl);

    create sequence plan_party_seq;
    create table plan_party(
        id          bigint primary key default nextval('plan_party_seq'),

        plan       bigint not null
            references plan(id) on update cascade on delete set null,

        person      int
            references person(id) on update cascade on delete set null,

        org         int
            references org(id) on update cascade on delete set null,

        description varchar(60)
    );

--\mixin lily.template.a-tag plan
--\mixin lily.template.a-tags plan bigint subject
--\mixin lily.template.a-parm plan
--\mixin lily.template.a-parms plan bigint subject
--\mixin lily.template.a-favs plan bigint subject
--\mixin lily.template.a-votes plan bigint subject
