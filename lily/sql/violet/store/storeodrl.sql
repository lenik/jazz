--\import violet.store.art
--\import violet.store.region
--\import violet.store.storeodr

    create sequence storeodrl_seq start with 1000;
    create table storeodrl(
        id          bigint primary key default nextval('storeodrl_seq'),
--#\mixin lily.mixin.Label
--\mixin lily.mixin.Ex
--\mixin lily.mixin.Mi

        odr         bigint not null
            references storeodr(id) on update cascade on delete cascade,

        art         int not null
            references art(id) on update cascade,

        region        int not null
            references region(id) on update cascade,

--\mixin violet.store._batch

        -- start-serial for the batch.
        serial      bigint,
        expire      timestamp,  -- t0 + art[ "life.shelf" ]

        qty         numeric(20,2) not null default 1,
        price       numeric(20,2) not null default 0,
        total       numeric(20,2) not null default 0,   -- cache

        comment     varchar(200)
    );

    -- trigger support
        create or replace function update_storeodr_stat() returns trigger as $$
        declare
            v record;
            c bigint;
            cqty real;
            ctotal real;
        begin
            for v in select count(*) "rows", sum(qty) "qty_sum", sum(total) "total_sum"
                from storeodrl where odr=new.odr
            loop
                c := v."rows";
                cqty := v.qty_sum;
                ctotal := v.total_sum;
            end loop;

            update storeodr set
                size = c, qty = cqty, total = ctotal
                where id = new.odr;
            return new;
        end $$ language plpgsql;

        create trigger storeodrl_insert
            after insert or delete on storeodrl
            for each row execute procedure update_storeodr_stat();

        create trigger storeodrl_update
            after update of qty, price on storeodrl
            for each row execute procedure update_storeodr_stat();
