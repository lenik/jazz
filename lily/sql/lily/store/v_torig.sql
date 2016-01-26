--\import lily.store.torig

    create or replace view v_torigs as
        select year, art, sum(qty) from torig group by year, art;

    create or replace view v_torig as
        select a.id, a.year,
            a.art, r.label "art_label",
            a.room, p.label "room_label",
            a.batch,
            a.qty
        from torig a
            left join art r on a.art=r.id
            left join room p on a.room=p.id;
