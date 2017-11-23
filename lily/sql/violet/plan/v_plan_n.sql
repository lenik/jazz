--\import violet.plan.plan
--\import violet.shop.saleodr

    create or replace view v_plan_n as select
        (select count(*) from plan) "total",
        (select count(distinct plan) from saleodr) "ordered";
