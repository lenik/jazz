--\import violet.store.artcat
--\import violet.store.art

    create sequence region_seq start with 1000;
    create table region(
        id          int primary key default nextval('region_seq'),
        code        varchar(10),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Props

        -- null proto for system type
        proto       int
            references region(id) on update cascade on delete set null,

        parent      int
            references region(id) on update cascade on delete cascade,

        depth       int not null default -1, -- || ancestors ||

        -- to estimate the scale of the region tree.
        height      int not null default -1, -- || longest descendant ||

        -- the artifact used as the region body.
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

    create index region_depth         on region(depth);
    create index region_label         on region(label);
    create index region_lastmod       on region(lastmod desc);
    create index region_priority      on region(priority);
    create index region_state         on region(state);
    create index region_uid_acl       on region(uid, acl);

    -- Predefined prototypes.
    insert into region(id, label) values(1, 'Abstract');
    insert into region(id, label) values(2, 'Template');
