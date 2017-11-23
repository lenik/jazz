--\import violet.store.art
--\import violet.ship.shipodr

    create sequence shipitem_seq start with 1000;
    create table shipitem(
        id          bigint primary key default nextval('shipitem_seq'),
--\mixin lily.mixin.Ex

        odr         int not null
            references shipodr(id) on update cascade on delete cascade,

        art         int
            references art(id) on update cascade on delete set null,

        qty         numeric(20,2) not null,
        price       numeric(20,2) not null default 0,
        total       numeric(20,2) not null default 0   -- cache
    );

    -- trigger support
        create or replace function update_shipodr_stat() returns trigger as $$
        declare
            v record;
            c int;
            cqty real;
            ctotal real;
        begin
            for v in select count(*) "count", sum(qty) "qty", sum(total) "total"
                from shipitem where doc=new.doc
            loop
                c := v."count";
                cqty := v.qty;
                ctotal := v.total;
            end loop;

            update shipodr set
                size = c, qty = cqty, total = ctotal
                where id = new.doc;
            return new;
        end $$ language plpgsql;

        create trigger shipitem_insert
            after insert or delete on shipitem
            for each row execute procedure update_shipodr_stat();

        create trigger shipitem_update
            after update of qty, price on shipitem
            for each row execute procedure update_shipodr_stat();
