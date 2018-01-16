--\import violet.art.art
--\import violet.store.region

    -- (cache) available store listing
    create sequence storel_seq start with 1000;
    create table storel(
        id          bigint primary key default nextval('storel_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.Ex
--\mixin lily.mixin.Ver

        art         int not null
            references art(id) on update cascade,

        region      int not null
            references region(id) on update cascade,

--\mixin violet.store._batch

        qty         numeric(20,2) not null
    );

    create index storel_lastmod     on storel(lastmod desc);
    create index storel_priority    on storel(priority);
    create index storel_state       on storel(state);
    create index storel_uid_acl     on storel(uid, acl);
  --create index storel_uom         on storel(uom);
