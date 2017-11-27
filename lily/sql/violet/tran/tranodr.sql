--\import lily.contact.org
--\import lily.contact.person
--\mixin lily.template.a-cat tran
--\mixin lily.template.a-phase tran

    create sequence tranodr_seq start with 1000;
    create table tranodr(             -- sales/subscription doc
        id          int primary key default nextval('tranodr_seq'),

--\mixin lily.mixin.Acl_rw-r-----
--\mixin lily.mixin.Ex

        -- t0: package date
        -- t1: recv date
--\mixin lily.mixin.Mi
--\mixin lily.mixin.Ver

--\mixin lily.mixin.Msg
--\mixin lily.mixin.Props


        cat         int not null
            references trancat(id) on update cascade,

        phase       int not null
            references tranphase(id) on update cascade,

        prev        int             -- previous order
            references tranodr(id) on update cascade on delete set null,

        saleodr     int
            references saleodr(id) on update cascade,
        storeodr    int
            references storeodr(id) on update cascade,

        shipper     int
            references org(id) on update cascade on delete set null,

--\mixin lily.mixin.Contact s_
--\mixin lily.mixin.Contact d_

    -- props:
        -- logs: any related info, including grabbed data.
        shipcost    numeric(20, 2) not null default 0,

        size        int not null default 0,
        qty         numeric(20,2) not null default 0,
        total       numeric(20,2) not null default 0
    );

    create index tranodr_lastmod          on tranodr(lastmod desc);
    create index tranodr_priority         on tranodr(priority);
    create index tranodr_state            on tranodr(state);
    create index tranodr_subject          on tranodr(subject);
    create index tranodr_uid_acl          on tranodr(uid, acl);
    create index tranodr_t0t1             on tranodr(t0, t1);
    create index tranodr_t1               on tranodr(t1);
