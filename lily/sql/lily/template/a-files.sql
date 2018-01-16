--\import lily.io.storage

--*mixin lily.template.a-dirs $1

    create sequence $1_dir_seq start with 1000;
    create table $1_dir(
        id          int primary key default nextval('$1_dir_seq'),
    --\mixin lily.mixin.LabelExVer
        -- label can be null.
    --\mixin lily.mixin.Props

        -- where the file data is stored.
        -- the actual storage layout is undefined.
        -- it can be '/data/b/ba/name' or '/data/dir/name' or whatever.
        storage     int
            references storage(id) on update cascade,

        parent      int not null
            references $1_dir(id) on update cascade on delete cascade,
        name        varchar(50) not null,
        
        -- cache
        level       int,
        path        varchar(250) not null
    );

    create index $1_dir_parent_name on $1_dir(parent, name);
    create index $1_dir_name        on $1_dir(name);

    create sequence $1_file_seq start with 1000;
    create table $1_file(
        id          int primary key default nextval('$1_file_seq'),
--\mixin lily.mixin.Ex
--\mixin lily.mixin.Ver
--\mixin lily.mixin.Props
        -- image.small/medium/large for thumbnails
        -- mime.type
        -- mime.encoding
        -- digest.sha1

        -- the entity using this file
        entity      ${2=int} not null
            references "$1"(id) on update cascade on delete cascade,

        dir         int not null
            references $1_dir(id) on update cascade,
        name        varchar(50) not null,
        path        varchar(250) not null,

        size        bigint not null,
        
        -- cache: null if not computed.
        crc         int
    );

    create index $1_file_dirname    on $1_file(dir, name);
    create index $1_file_name       on $1_file(name);
    create index $1_file_size       on $1_file(size);
    create index $1_file_crc        on $1_file(crc);

    create or replace view v_$1_file as
        select
            a.*,
            o.${3=label} o_${3=label}
        from $1_file a
            left join "$1" o on a.entity=o.id
            ;
            
