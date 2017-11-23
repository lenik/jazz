--\import lily.contact.org
--\import lily.contact.person
--\import violet.plan.plan
--\mixin lily.template.a-cat sale
--\mixin lily.template.a-phase sale

    create sequence saleodr_seq start with 1000;
    create table saleodr(                  -- sales/subscription doc
        id          bigint primary key default nextval('saleodr_seq'),

--\mixin lily.mixin.Acl_rw-r-----
--\mixin lily.mixin.Ex
        -- contract date .. deadline
--\mixin lily.mixin.Mi
        -- subscription category/phase
--\mixin lily.mixin.Ver
--\mixin lily.mixin.Msg

        cat         int not null
            references salecat(id) on update cascade,

        phase       int not null
            references salephase(id) on update cascade,

        prev        int             -- previous doc
            references saleodr(id) on update cascade on delete set null,

        plan        int
            references plan(id) on update cascade on delete set null,

        -- customer org.
        org         int
            references org(id) on update cascade,

        -- customer person.
        person      int
            references person(id) on update cascade,

        size        int not null default 0,
        qty         numeric(20,2) not null default 0,
        total       numeric(20,2) not null default 0
    );

    create index saleodr_lastmod          on saleodr(lastmod desc);
    create index saleodr_priority         on saleodr(priority);
    create index saleodr_state            on saleodr(state);
    create index saleodr_subject          on saleodr(subject);
    create index saleodr_uid_acl          on saleodr(uid, acl);
    create index saleodr_t0t1             on saleodr(t0, t1);
    create index saleodr_t1               on saleodr(t1);
