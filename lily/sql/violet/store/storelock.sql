--\import violet.art.art
--\import violet.store.region
--\import violet.fab.fabproc

    create sequence storelock_seq start with 1000;
    create table storelock(
        id          bigint primary key default nextval('storelock_seq'),
--\mixin lily.mixin.ExVer
        -- state: NORMAL, INVALID
        -- INVALID: when storeodr(take-out) created.
        -- INVALID records should be deleted.

        -- holders
        h_fabproc bigint
            references fabproc(id) on update cascade on delete cascade,

        art         int not null
            references art(id) on update cascade,

        region      int not null
            references region(id) on update cascade,

--\mixin violet.store._batch

        -- start-serial for the batch.
        -- see also artcat.serialfmt
        serial      bigint,
        -- t0 + art[ "life.shelf" ]
        expire      timestamp with time zone,

        qty         numeric(20,2) not null default 1
    );
