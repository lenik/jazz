--\import lily.inc.msg
--\import lily.contact.org
--\import lily.contact.person
--\import violet.plan.plan
--\mixin lily.template.a-cat store
--\mixin lily.template.a-phase store

    create sequence storeodr_seq start with 1000;
    create table storeodr(
        id          bigint primary key default nextval('storeodr_seq'),

--\mixin lily.mixin.Acl_rw-r-----
--\mixin lily.mixin.ExVer
--\mixin lily.mixin.Mi
--\mixin lily.mixin.Msg

        -- cat. stock order subject
        cat         int not null
            references storecat(id) on update cascade,

        -- phase. stock stage
        phase       int not null
            references storephase(id) on update cascade,

        prev        bigint              -- previous odr
            references storeodr(id) on update cascade on delete set null,

        plan        int
            references plan(id) on update cascade on delete set null,

        org         int
            references org(id) on update cascade,

        ou          int,

        person      int
            references person(id) on update cascade,

        size        int not null default 0,
        qty         numeric(20,2) not null default 0,
        total       numeric(20,2) not null default 0
    );

    create index storeodr_lastmod         on storeodr(lastmod desc);
    create index storeodr_priority        on storeodr(priority);
    create index storeodr_state           on storeodr(state);
    create index storeodr_subject         on storeodr(subject);
    create index storeodr_uid_acl         on storeodr(uid, acl);
    create index storeodr_t0t1            on storeodr(t0, t1);
    create index storeodr_t1              on storeodr(t1);
