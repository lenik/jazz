--\import lily.inc.msg
--\import lily.contact.org
--\import lily.contact.person
--\import violet.plan.plan

    create sequence fabodr_seq start with 1000;
    create table fabodr(
        id          bigint primary key default nextval('fabodr_seq'),
--\mixin lily.mixin.Acl_rw-r-----
--\mixin lily.mixin.ExVer
--\mixin lily.mixin.Mi
--\mixin lily.mixin.Msg

        plan        bigint
            references plan(id) on update cascade on delete set null,

        -- customer org.
        custom_org  int
            references org(id) on update cascade,

        -- customer person.
        custom      int
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

    create index fabodr_lastmod          on fabodr(lastmod desc);
    create index fabodr_priority         on fabodr(priority);
    create index fabodr_state            on fabodr(state);
    create index fabodr_subject          on fabodr(subject);
    create index fabodr_uid_acl          on fabodr(uid, acl);
    create index fabodr_t0t1             on fabodr(t0, t1);
    create index fabodr_t1               on fabodr(t1);
