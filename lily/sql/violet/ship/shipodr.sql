--\import lily.contact.org
--\import lily.contact.person
--\mixin lily.template.a-cat ship
--\mixin lily.template.a-phase ship

    create sequence shipodr_seq start with 1000;
    create table shipodr(             -- sales/subscription doc
        id          int primary key default nextval('shipodr_seq'),

--\mixin lily.mixin.Acl_rw-r-----
--\mixin lily.mixin.Ex

        -- t0: package date
        -- t1: recv date
--\mixin lily.mixin.Mi
--\mixin lily.mixin.Ver

--\mixin lily.mixin.Msg
--\mixin lily.mixin.Props


        cat         int not null
            references shipcat(id) on update cascade,

        phase       int not null
            references shipphase(id) on update cascade,

        prev        int             -- previous order
            references shipodr(id) on update cascade on delete set null,

        saleodr     int,

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

    create index shipodr_lastmod          on shipodr(lastmod desc);
    create index shipodr_priority         on shipodr(priority);
    create index shipodr_state            on shipodr(state);
    create index shipodr_subject          on shipodr(subject);
    create index shipodr_uid_acl          on shipodr(uid, acl);
    create index shipodr_t0t1             on shipodr(t0, t1);
    create index shipodr_t1               on shipodr(t1);
