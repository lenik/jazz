--\import lily.store.art
--\import lily.store.room
--\import lily.store.todr

    create sequence tentry_seq start with 1000;
    create table tentry(
        id          bigint primary key default nextval('tentry_seq'),
--#\mixin lily.mixin.Label
--\mixin lily.mixin.Ex
--\mixin lily.mixin.Mi

        odr         bigint not null
            references todr(id) on update cascade on delete cascade,

        art         int not null
            references art(id) on update cascade,

        room        int not null
            references room(id) on update cascade,

--\mixin lily.store._batch

        -- start-serial for the batch.
        serial      bigint,
        expire      timestamp,

        qty         numeric(20,2) not null default 1,
        price       numeric(20,2) not null default 0,
        total       numeric(20,2) not null default 0,   -- cache

        comment     varchar(200)
    );

    -- trigger support
        create or replace function update_todr_stat() returns trigger as $$
        declare
            v record;
            c bigint;
            cqty real;
            ctotal real;
        begin
            for v in select count(*) "rows", sum(qty) "qty_sum", sum(total) "total_sum"
                from tentry where odr=new.odr
            loop
                c := v."rows";
                cqty := v.qty_sum;
                ctotal := v.total_sum;
            end loop;

            update todr set
                size = c, qty = cqty, total = ctotal
                where id = new.odr;
            return new;
        end $$ language plpgsql;

        create trigger tentry_insert
            after insert or delete on tentry
            for each row execute procedure update_todr_stat();

        create trigger tentry_update
            after update of qty, price on tentry
            for each row execute procedure update_todr_stat();
