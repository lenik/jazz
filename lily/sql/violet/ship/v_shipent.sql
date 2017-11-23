--\import violet.ship.shipitem

    create or replace view v_shipitem as
        select a.*,
            shipodr.subject "doc_subject",
            art.label "art_label",
            art.model "art_model"
        from shipitem a
            left join shipodr on a.odr=shipodr.id
            left join art on a.art=art.id;
