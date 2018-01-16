--\import violet.art.art
--\import violet.tran.tranodr

    create sequence tranodrl_seq start with 1000;
    create table tranodrl(
        id          bigint primary key default nextval('tranodrl_seq'),
--\mixin lily.mixin.Ex

        odr         int not null
            references tranodr(id) on update cascade on delete cascade,

        art         int
            references art(id) on update cascade on delete set null,

        qty         numeric(20,2) not null,
        price       numeric(20,2) not null default 0,
        amount      numeric(20,2) not null default 0   -- cache
    );

    -- trigger support
        create or replace function update_tranodr_stat() returns trigger as $$
        declare
            v record;
            c int;
            cqty real;
            camount real;
        begin
            for v in select count(*) "count", sum(qty) "sum_qty", sum(amount) "sum_amount"
                from tranodrl where doc=new.doc
            loop
                c := v."count";
                cqty := v.sum_qty;
                camount := v.sum_amount;
            end loop;

            update tranodr set
                length = c, sum_qty = cqty, sum_amount = camount
                where id = new.doc;
            return new;
        end $$ language plpgsql;

        create trigger tranodrl_insert
            after insert or delete on tranodrl
            for each row execute procedure update_tranodr_stat();

        create trigger tranodrl_update
            after update of qty, price on tranodrl
            for each row execute procedure update_tranodr_stat();
