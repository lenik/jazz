--\import violet.store.storeini

    create or replace view v_storeinis as
        select year, art, sum(qty) from storeini group by year, art;

    create or replace view v_storeini as
        select a.id, a.year,
            a.art, r.label "art_label",
            a.room, p.label "room_label",
            a.batch,
            a.qty
        from storeini a
            left join art r on a.art=r.id
            left join room p on a.room=p.id;
