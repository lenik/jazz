--\import lily.contact.org
--\import lily.contact.person
--\import violet.plan.plan

    create sequence manuodr_seq start with 1000;
    create table manuodr(                  -- sales/subscription doc
        id          bigint primary key default nextval('manuodr_seq'),
--\mixin lily.mixin.Acl_rw-r-----
--\mixin lily.mixin.ExVer
--\mixin lily.mixin.Mi
--\mixin lily.mixin.Msg

        plan        bigint
            references plan(id) on update cascade on delete set null,

        -- customer org.
        cust_org    int
            references org(id) on update cascade,

        -- customer person.
        cust_person int
            references person(id) on update cascade,

        -- salesman
        clerk       int
            references person(id) on update cascade,
        
        nitem       int not null default 0,
        sum_qty     numeric(20,2) not null default 0,
        sum_amount  numeric(20,2) not null default 0,
        
        ntask       int,        -- cache
        nproc       int,        -- cache
        ntrack      int         -- cache
    );

    create index manuodr_lastmod          on manuodr(lastmod desc);
    create index manuodr_priority         on manuodr(priority);
    create index manuodr_state            on manuodr(state);
    create index manuodr_subject          on manuodr(subject);
    create index manuodr_uid_acl          on manuodr(uid, acl);
    create index manuodr_t0t1             on manuodr(t0, t1);
    create index manuodr_t1               on manuodr(t1);
