--\import violet.art.art
--\import violet.store.region

    create sequence asset_$1_seq start with 1000;
    create table asset_$1(
        id          bigint primary key default nextval('asset_$1_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.Ex
--\mixin lily.mixin.Ver
--\mixin lily.mixin.Mi
        -- t0: owned since, t1: lost after

        owner       ${3=int} not null
            references $2(id) on update cascade on delete cascade,
        
        -- config/preference artcat for auto-create asset_$1 arts.
        art         int not null
            references art(id) on update cascade,

        -- region with-in specific root.
        region      int
            references region(id) on update cascade,

--\mixin violet.store._batch

        qty         numeric(20,2) not null,
        -- see also artcat.serialfmt
        serial      bigint,
        expire      timestamp with time zone
    );

    create index asset_$1_lastmod     on asset_$1(lastmod desc);
    create index asset_$1_priority    on asset_$1(priority);
    create index asset_$1_state       on asset_$1(state);
    create index asset_$1_uid_acl     on asset_$1(uid, acl);
  --create index asset_$1_uom         on asset_$1(uom);
