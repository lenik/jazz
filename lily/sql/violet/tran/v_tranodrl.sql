--\import violet.tran.tranodrl

    create or replace view v_tranodrl as
        select a.*,
            tranodr.subject "odr_subject",
            art.label "art_label",
            art.model "art_model"
        from tranodrl a
            left join tranodr on a.odr=tranodr.id
            left join art on a.art=art.id;
