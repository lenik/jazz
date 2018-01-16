--\import violet.manu.manuodr

    create sequence manutask_seq start with 1000;
    create table manutask(
        id          int primary key default nextval('manutask_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Mi
        -- t0: actual working start date
        -- t1: actual working finish date

        -- Cancel process is required to cancel the order.
        odr         bigint not null
            references manuodr(id) on update cascade,
        
        since       timestamp with time zone not null,  -- schedule start date
        deadline    timestamp with time zone not null,  -- schedule end date
        
        -- storelock: create locks for manuproc* should be finer.
        -- nlock       bigint
        nproc       int,        -- cache
        ntrack      int         -- cache
    );

    create index manutask_label       on manutask(label);
    create index manutask_lastmod     on manutask(lastmod desc);
    create index manutask_priority    on manutask(priority);
    create index manutask_state       on manutask(state);
    create index manutask_uid_acl     on manutask(uid, acl);
