--\import violet.sale.saleodr
--\import violet.ship.shipodr

    create or replace view v_saleodr_n as select
        (select count(*) from saleodr) "total",
        (select count(distinct saleodr) from shipodr) "shipped";
