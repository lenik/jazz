--\import lily.account
--\import violet.art.artmodel
--\import violet.fab.fabproc

    create sequence fabtrack_seq start with 1000;
    create table fabtrack(
        id          bigint primary key default nextval('fabtrack_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Mi
        -- t0: actual start date
        -- t1: actual finish date

        -- parent of the track, the merged one.
        proc        bigint not null
            references fabproc(id) on update cascade on delete cascade,
        since       timestamp with time zone not null,  -- schedule start date
        deadline    timestamp with time zone not null,  -- schedule end date
        
        qty_plan    numeric(20, 2) not null,
        qty_actual  numeric(20, 2) not null,
        qty_valid   numeric(20, 2) not null,
        
        ou          int
            references orgunit(id) on update cascade on delete cascade
    );

    create index fabtrack_label       on fabtrack(label);
    create index fabtrack_lastmod     on fabtrack(lastmod desc);
    create index fabtrack_priority    on fabtrack(priority);
    create index fabtrack_state       on fabtrack(state);
    create index fabtrack_uid_acl     on fabtrack(uid, acl);
