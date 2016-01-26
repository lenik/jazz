--\import lily.store.art
--\import lily.store.artcat
--\import lily.store.room

    create or replace view v_room_ls as select
        u.*
        from (
                select cat, null "art", id "room" from room where cat is not null
                union
                select null "cat", art, id "room" from room where art is not null
            ) u left join room on u.room=room.id;

