--\import lily.inc.msg
--\import lily.contact.org
--\import lily.contact.person

    create sequence todr_seq start with 1000;
    create table todr(
        id          bigint primary key default nextval('todr_seq'),

--\mixin lily.mixin.Acl_rw-r-----
--\mixin lily.mixin.ExVer
--\mixin lily.mixin.Mi
        -- cat. stock order subject
        -- phase. stock stage
--\mixin lily.mixin.Msg

        prev        bigint              -- previous odr
            references todr(id) on update cascade on delete set null,

--        topic       int
--            references topic(id) on update cascade on delete set null,

        org         int
            references org(id) on update cascade,

        ou          int,

        person      int
            references person(id) on update cascade,

        size        int not null default 0,
        qty         numeric(20,2) not null default 0,
        total       numeric(20,2) not null default 0
    );

    create index todr_lastmod         on todr(lastmod desc);
    create index todr_priority        on todr(priority);
    create index todr_state           on todr(state);
    create index todr_subject         on todr(subject);
    create index todr_uid_acl         on todr(uid, acl);
    create index todr_t0t1            on todr(t0, t1);
    create index todr_t1              on todr(t1);
