--\import lily.store.artcat
--\import lily.store.art

    create sequence room_seq start with 1000;
    create table room(
        id          int primary key default nextval('room_seq'),
        code        varchar(10),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Props

        -- null proto for system type
        proto       int
            references room(id) on update cascade on delete set null,

        parent      int
            references room(id) on update cascade on delete cascade,

        depth       int not null default -1, -- || ancestors ||

        -- to estimate the scale of the room tree.
        height      int not null default -1, -- || longest descendant ||

        -- the artifact used as the room body.
        body        int
            references art(id) on update cascade,

        -- reserved for this artifact category.
        cat         int
            references artcat(id) on update cascade on delete set null,

        -- reserved/locked for this artifact.
        art         int     -- state=LOCKED if necessary.
            references art(id) on update cascade on delete set null,

        -- mm
        x           int not null default 0,
        y           int not null default 0,
        z           int not null default 0,
        dx          int not null default 0,
        dy          int not null default 0,
        dz          int not null default 0
    );

    create index room_depth         on room(depth);
    create index room_label         on room(label);
    create index room_lastmod       on room(lastmod desc);
    create index room_priority      on room(priority);
    create index room_state         on room(state);
    create index room_uid_acl       on room(uid, acl);

    -- Predefined prototypes.
    insert into room(id, label) values(1, 'Abstract');
    insert into room(id, label) values(2, 'Template');
