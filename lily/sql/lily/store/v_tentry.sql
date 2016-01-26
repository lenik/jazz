--\import lily.store.tentry

    create or replace view v_tentry as
        select a.*,
            todr.subject "odr_subject",
            art.label "art_label",
            room.label "room_label"
        from tentry a
            left join todr on a.odr=todr.id
            left join art on a.art=art.id
            left join room on a.room=room.id;
