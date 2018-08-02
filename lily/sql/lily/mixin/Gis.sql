
    -- Column-Group: Gis
        lng         float,  -- longitude/X
        lat         float,  -- latitude/Y
        _zone       int     -- (cache)
            references zone(id) on update cascade,

