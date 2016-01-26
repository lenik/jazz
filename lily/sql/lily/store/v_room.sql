--\import lily.store.room

    create or replace view v_room as
        select room.*, p.label "parent_label"
        from room
            left join room p on room.parent=p.id;

    create or replace view v_room_n as select
        (select count(*) from room) total,
        (select count(*) from room where art is not null) used,
        (select count(*) from room where art is not null and state=1) locked;

