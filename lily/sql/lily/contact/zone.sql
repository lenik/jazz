--\import lily.account

    create sequence zone_seq start with 1000;
    create table zone(
        id          int primary key default nextval('zone_seq'),
        code        varchar(10) unique,
--\mixin lily.mixin.LabelExVer

        country     char(2),
        parent      int
            references zone(id) on update cascade on delete cascade,
        depth       int,

        telcode     varchar(10),
        postcode    varchar(10),

        --population  float,          -- million
        --area        float,          -- km^2

        data        jsonb
    );

    create index zone_label          on zone(label);
    create index zone_depth          on zone(depth);
    create index zone_postcode       on zone(postcode);
