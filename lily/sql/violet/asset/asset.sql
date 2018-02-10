--\import violet.art.art
--\import violet.store.region

    create sequence asset_seq start with 1000;
    create table asset(
        id          bigint primary key default nextval('asset_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.Ex
--\mixin lily.mixin.Ver
--\mixin lily.mixin.Mi
        -- t0: owned since, t1: lost after

        -- config/preference artcat for auto-create asset arts.
        art         int not null
            references art(id) on update cascade,

        -- region with-in specific root.
        region      int
            references region(id) on update cascade,

--\mixin violet.store._batch

        qty         numeric(20,2) not null,
        -- see also artcat.serialfmt
        serial      bigint,
        expire      timestamp with time zone,
        
        o_user      int
            references "user"(id) on update cascade,
        o_group     int
            references "group"(id) on update cascade,
        o_org       int
            references org(id) on update cascade,
        o_orgunit   int
            references orgunit(id) on update cascade,
        o_person    int
            references person(id) on update cascade
    );

    create index asset_lastmod     on asset(lastmod desc);
    create index asset_priority    on asset(priority);
    create index asset_state       on asset(state);
    create index asset_uid_acl     on asset(uid, acl);
  --create index asset_uom         on asset(uom);
