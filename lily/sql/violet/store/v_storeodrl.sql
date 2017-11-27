--\import violet.store.storeodrl

    create or replace view v_storeodrl as
        select a.*,
            storeodr.subject "odr_subject",
            art.label "art_label",
            region.label "region_label"
        from storeodrl a
            left join storeodr on a.odr=storeodr.id
            left join art on a.art=art.id
            left join region on a.region=region.id;
