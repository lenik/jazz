--\import violet.art.art
--\import violet.art.artcat
--\import violet.store.region

    create or replace view v_region_ls as select
        u.*
        from (
                select cat, null "art", id "region" from region where cat is not null
                union
                select null "cat", art, id "region" from region where art is not null
            ) u left join region on u.region=region.id;

