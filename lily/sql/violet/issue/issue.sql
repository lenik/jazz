--\import lily.inc.msg
--\import lily.contact.org
--\import lily.contact.person
--\mixin lily.template.a-cat issue
--\mixin lily.template.a-phase issue

    create sequence issue_seq start with 1000;
    create table issue(
        id          bigint primary key default nextval('issue_seq'),

--\mixin lily.mixin.Acl_rw-r-----
--\mixin lily.mixin.Ex
--\mixin lily.mixin.Mi
--\mixin lily.mixin.Msg
--\mixin lily.mixin.Ver

        cat         int not null
            references issuecat(id) on update cascade,

        phase       int not null
            references issuephase(id) on update cascade,

        nread       int not null default 0,
        nvote       int not null default 0,
        nlike       int not null default 0,

        value       double precision not null default 0 -- estimated
        -- valmax   double precision,
    );

    create index issue_lastmod        on issue(lastmod desc);
    create index issue_priority_nvote on issue(priority, nvote desc);
    create index issue_state          on issue(state);
    create index issue_subject        on issue(subject);
    create index issue_t0t1           on issue(t0, t1);
    create index issue_t1             on issue(t1);
    create index issue_uid_acl        on issue(uid, acl);

    create sequence issue_party_seq;
    create table issue_party(
        id          bigint primary key default nextval('issue_party_seq'),

        issue       bigint not null
            references issue(id) on update cascade on delete set null,

        person      int
            references person(id) on update cascade on delete set null,

        org         int
            references org(id) on update cascade on delete set null,

        description varchar(60)
    );

--\mixin lily.template.a-tag issue
--\mixin lily.template.a-tags issue bigint subject
--\mixin lily.template.a-parm issue
--\mixin lily.template.a-parms issue bigint subject
--\mixin lily.template.a-favs issue bigint subject
--\mixin lily.template.a-votes issue bigint subject
