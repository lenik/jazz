--\import lily.account
--\import lily.geo.zonecat

    create sequence zone_seq start with 10000;
    create table zone(
        id          int primary key default nextval('zone_seq'),
        code        varchar(10) unique, -- Code: eg. 331021102
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer

        -- default 'abstract'
        cat         int not null default 1
            references zonecat on update cascade,

        country     char(2),
        parent      int
            references zone on update cascade on delete cascade,
        depth       int not null default -1,

        telcode     varchar(10),
        postcode    varchar(10),

        -- Properties:
            -- images:      UploadedFile[]
            -- outlines:    Point[][]
            -- population:  float -- million
            -- area:        float -- km^2
--\mixin lily.mixin.PropFiles

        data        jsonb
    );

    create index zone_label          on zone(label);
    create index zone_depth          on zone(depth);
    create index zone_postcode       on zone(postcode);

    insert into zone(id, cat, label) values
        (0, 1, 'World'),
        (86, 2, 'China');

