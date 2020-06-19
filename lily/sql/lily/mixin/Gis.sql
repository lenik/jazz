
    -- Column-Group: Gis
        lng         float,  -- longitude/X
        lat         float,  -- latitude/Y
        _zone       int     -- (cache)
            references zone on update cascade,

