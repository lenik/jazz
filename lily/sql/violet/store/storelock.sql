--\import violet.art.art
--\import violet.store.region
--\import violet.manu.manuproc

    create sequence storelock_seq start with 1000;
    create table storelock(
        id          bigint primary key default nextval('storelock_seq'),
--\mixin lily.mixin.ExVer
        -- state: NORMAL, INVALID
        -- INVALID: when storeodr(take-out) created.
        -- INVALID records should be deleted.

        -- holders
        h_manuproc bigint
            references manuproc(id) on update cascade on delete cascade,

        art         int not null
            references art(id) on update cascade,

        region      int not null
            references region(id) on update cascade,

--\mixin violet.store._batch

        -- start-serial for the batch.
        -- see also artcat.serialfmt
        serial      bigint,
        expire      timestamp,  -- t0 + art[ "life.shelf" ]

        qty         numeric(20,2) not null default 1
    );
