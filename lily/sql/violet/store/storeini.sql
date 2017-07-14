--\import violet.store.art
--\import violet.store.room

    create sequence storeini_seq start with 1000;
    create table storeini(
        id          int primary key default nextval('storeini_seq'),
        year        int not null,

        art         int not null
            references art(id) on update cascade on delete set null,

        room        int not null
            references room(id) on update cascade on delete set null,

--\mixin violet.store._batch

        qty         numeric(20,2) not null,

        constraint storeini_uk unique(year, art, room, batch)
    );
