--\import violet.fab.fabodr

    create sequence fabtask_seq start with 1000;
    create table fabtask(
        id          bigint primary key default nextval('fabtask_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Mi
        -- t0: actual working start date
        -- t1: actual working finish date

        -- Cancel process is required to cancel the order.
        odr         bigint not null
            references fabodr(id) on update cascade,
        
        since       timestamp with time zone not null,  -- schedule start date
        deadline    timestamp with time zone not null,  -- schedule end date
        
        -- storelock: create locks for fabproc* should be finer.
        -- nlock       bigint
        nproc       int,        -- cache
        ntrack      int         -- cache
    );

    create index fabtask_label       on fabtask(label);
    create index fabtask_lastmod     on fabtask(lastmod desc);
    create index fabtask_priority    on fabtask(priority);
    create index fabtask_state       on fabtask(state);
    create index fabtask_uid_acl     on fabtask(uid, acl);
