--\import violet.art.art
--\import violet.store.storeodrl

    create or replace view v_art_n as
        select
            (select count(*) from art) total,
            (select count(distinct art) from storeodrl) used;
