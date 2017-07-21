--\import violet.store.region

    create or replace view v_region as
        select region.*, p.label "parent_label"
        from region
            left join region p on region.parent=p.id;

    create or replace view v_region_n as select
        (select count(*) from region) total,
        (select count(*) from region where art is not null) used,
        (select count(*) from region where art is not null and state=1) "locked";

