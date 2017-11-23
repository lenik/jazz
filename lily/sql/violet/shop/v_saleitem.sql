--\import violet.shop.saleitem

    create or replace view v_saleitem as
        select a.*,
            saleodr.subject "odr_subject",
            art.label "art_label",
            art.model "art_model"
        from saleitem a
            left join saleodr on a.odr=saleodr.id
            left join art on a.art=art.id;
