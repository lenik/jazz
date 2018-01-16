--\import lily.account
--\import violet.art.artmodel
--\import violet.manu.manuproc

    create sequence manutrack_seq start with 1000;
    create table manutrack(
        id          int primary key default nextval('manutrack_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Mi
        -- t0: actual start date
        -- t1: actual finish date

        -- parent of the track, the merged one.
        parent      bigint not null
            references manuproc(id) on update cascade on delete cascade,
        since       timestamp with time zone not null,  -- schedule start date
        deadline    timestamp with time zone not null,  -- schedule end date
        
        qty_plan    numeric(20, 2) not null,
        qty_actual  numeric(20, 2) not null,
        qty_valid   numeric(20, 2) not null,
        
        ou          int
            references orgunit(id) on update cascade on delete cascade
    );

    create index manutrack_label       on manutrack(label);
    create index manutrack_lastmod     on manutrack(lastmod desc);
    create index manutrack_priority    on manutrack(priority);
    create index manutrack_state       on manutrack(state);
    create index manutrack_uid_acl     on manutrack(uid, acl);
