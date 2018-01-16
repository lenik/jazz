
    -- Column-Group: ACL
        uid         int references "user"(id) on update cascade on delete set null,
        gid         int references "group"(id) on update cascade on delete set null,
        mode        int not null default b'110100000'::int,
        acl         int,
