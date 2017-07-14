--\import violet.ship.shipent

    create or replace view v_shipent as
        select a.*,
            shipodr.subject "doc_subject",
            art.label "art_label",
            art.model "art_model"
        from shipent a
            left join shipodr on a.odr=shipodr.id
            left join art on a.art=art.id;
