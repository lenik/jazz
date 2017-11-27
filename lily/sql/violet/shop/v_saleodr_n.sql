--\import violet.shop.saleodr
--\import violet.tran.tranodr

    create or replace view v_saleodr_n as select
        (select count(*) from saleodr) "total",
        (select count(distinct saleodr) from tranodr) "ntran";
