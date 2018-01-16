--\import lily.io.storage

    create sequence $1_dir_seq start with 1000;
    create table $1_dir(
        id          int primary key default nextval('$1_file_seq'),
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
