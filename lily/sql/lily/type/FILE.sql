    create sequence $1_file_seq start with 1000;

    create table $1_file(
        id          int primary key default nextval('$1_file_seq'),
--\mixin lily.mixin.Ex
--\mixin lily.mixin.Ver
--\mixin lily.mixin.Props
        -- image.medium     for thumbnails
        -- mime.type
        -- mime.encoding
        -- digest.sha1

        obj         int not null
            references "$1"(id) on update cascade on delete cascade,

        -- where the file data is stored.
        -- the actual storage layout is undefined.
        -- it can be '/data/b/ba/basename' or '/data/dir/base' or whatever.
        storage     int not null,

        dirname     varchar(200),
        basename    varchar(50) not null,

        size        bigint not null,
        crc         int
    );

    create index $1_file_dirbase    on $1_file(dirname, basename);
    create index $1_file_base       on $1_file(basename);
    create index $1_file_size       on $1_file(size);
    create index $1_file_crc        on $1_file(crc);

    create or replace view v_$1_file as
        select
            a.*,
            o.${2=label} obj_${2=label}
        from $1_file a
            left join "$1" o on a.obj=o.id
            ;
