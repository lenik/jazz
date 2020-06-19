
    -- Column-Group: ACL
        uid         int references "user" on update cascade on delete set null,
        gid         int references "group" on update cascade on delete set null,
        mode        int not null default b'110100100'::int,
        acl         int,
