--\import violet.store.art
--\import violet.shop.saleodr
--\import violet.shop.shopitem

    create sequence saleitem_seq start with 1000;
    create table saleitem(
        id          bigint primary key default nextval('saleitem_seq'),
--\mixin lily.mixin.Ex
--\mixin lily.mixin.Mi

        odr         bigint not null
            references saleodr(id) on update cascade on delete cascade,

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
        total       numeric(20,2) not null default 0,   -- cache
        comment     varchar(200),
        footnote    varchar(200)
    );

    -- trigger support
        create or replace function update_saleodr_stat() returns trigger as $$
        declare
            v record;
            c int;
            cqty real;
            ctotal real;
        begin
            for v in select count(*) "count", sum(qty) "qty", sum(total) "total"
                from saleitem where doc=new.doc
            loop
                c := v."count";
                cqty := v.qty;
                ctotal := v.total;
            end loop;

            update saleodr set
                size = c, qty = cqty, total = ctotal
                where id = new.doc;
            return new;
        end $$ language plpgsql;

        create trigger saleitem_insert
            after insert or delete on saleitem
            for each row execute procedure update_saleodr_stat();

        create trigger saleitem_update
            after update of qty, price on saleitem
            for each row execute procedure update_saleodr_stat();
