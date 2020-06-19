
    -- Column-Group: Gis
        lng         int,    -- longitude/X
        lat         int,    -- latitude/Y
        _zone       int     -- (cache)
            references zone on update cascade,

