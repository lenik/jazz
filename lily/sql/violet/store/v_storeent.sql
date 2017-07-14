--\import violet.store.storeent

    create or replace view v_storeent as
        select a.*,
            storeodr.subject "odr_subject",
            art.label "art_label",
            room.label "room_label"
        from storeent a
            left join storeodr on a.odr=storeodr.id
            left join art on a.art=art.id
            left join room on a.room=room.id;
