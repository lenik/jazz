--\import violet.sale.saleent

    create or replace view v_saleent as
        select a.*,
            saleodr.subject "odr_subject",
            art.label "art_label",
            art.model "art_model"
        from saleent a
            left join saleodr on a.odr=saleodr.id
            left join art on a.art=art.id;
