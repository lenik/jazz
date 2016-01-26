--\import lily.store.art
--\import lily.store.room

    create sequence torig_seq start with 1000;
    create table torig(
        id          int primary key default nextval('torig_seq'),
        year        int not null,

        art         int not null
            references art(id) on update cascade on delete set null,

        room        int not null
            references room(id) on update cascade on delete set null,

--\mixin lily.store._batch

        qty         numeric(20,2) not null,

        constraint torig_uk unique(year, art, room, batch)
    );
