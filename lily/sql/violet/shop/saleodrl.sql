--\import violet.art.art
--\import violet.shop.saleodr
--\import violet.shop.shopitem

    create sequence saleodrl_seq start with 1000;
    create table saleodrl(
        id          bigint primary key default nextval('saleodrl_seq'),
--\mixin lily.mixin.Ex
--\mixin lily.mixin.Mi

        odr         bigint not null
            references saleodr(id) on update cascade on delete cascade,

        -- null if no corresponding shop item.
        shopitem    bigint
            references shopitem(id) on update cascade on delete set null,
        
        art         int not null
            references art(id) on update cascade,

--\mixin violet.store._batch

        resale      boolean not null default false,
        olabel      varchar(30),    -- label override
        ospec       varchar(80),    -- spec override

        qty         numeric(20,2) not null,
        price       numeric(20,2) not null default 0,
        amount      numeric(20,2) not null default 0,   -- cache
        
        notes       varchar(200)
    );

    -- trigger support
        create or replace function update_saleodr_stat() returns trigger as $$
        declare
            v record;
            c int;
            cqty real;
            camount real;
        begin
            for v in select count(*) "count", sum(qty) "sum_qty", sum(amount) "sum_amount"
                from saleodrl where doc=new.doc
            loop
                c := v."count";
                cqty := v.sum_qty;
                camount := v.sum_amount;
            end loop;

            update saleodr set
                length = c, sum_qty = cqty, sum_amount = camount
                where id = new.doc;
            return new;
        end $$ language plpgsql;

        create trigger saleodrl_insert
            after insert or delete on saleodrl
            for each row execute procedure update_saleodr_stat();

        create trigger saleodrl_update
            after update of qty, price on saleodrl
            for each row execute procedure update_saleodr_stat();
