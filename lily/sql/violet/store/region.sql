--\import violet.art.artcat
--\import violet.art.art
--\import violet.store.regionlevel
--\mixin lily.template.a-cat region
--\mixin lily.template.a-tag region

    create sequence region_seq start with 1000;
    create table region(
        id          int primary key default nextval('region_seq'),
        code        varchar(10),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.PropFiles

        path        varchar(200) not null default '?',

        -- null proto for system type
        -- otherwise referres to the template region.
        proto       int
            references region(id) on update cascade on delete set null,

        parent      int
            references region(id) on update cascade on delete cascade,

        depth       int not null default -1, -- Depth: || ancestors ||

        -- to estimate the scale of the region tree.
        height      int not null default -1, -- Height: || longest descendant ||

        level       int not null default 0
            references regionlevel(id) on update cascade,

        cat         int not null
            references regioncat(id) on update cascade,

        tag         int
            references regiontag(id) on update cascade on delete set null,

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
    insert into regioncat(id, label) values
        (0, 'Abstract'),
        (1, 'Information'),
        (2, 'Raw Material'),
        (3, 'Intermediates'),
        (4, 'Products'),
        (5, 'Waste');

    insert into region(id, cat, label) values
        (0, 0, 'Abstract Region Root'),
        (1, 0, 'Default Template Root'),
        (2, 0, 'Information'),
        (3, 0, 'Raw Material'),
        (4, 0, 'Intermediates'),
        (5, 0, 'Products'),
        (6, 0, 'Waste'),
        (7, 0, 'Sales');

--\mixin lily.template.a-tags region
