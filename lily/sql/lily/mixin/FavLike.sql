
    -- Column-Group: FavLike
        nfav        int not null default 0, -- Favorites

        -- nlike = nvote + nhate
        nvote       int not null default 0, -- Vote Ups
        nhate       int not null default 0, -- Vote Downs
