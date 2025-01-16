
    -- Column-Group: Gis
        lng         int,    -- Longitude: Longitude/X
        lat         int,    -- Latitude: Latitude/Y
        _zone       int     -- Default Zone: if any (cache)
            references zone on update cascade,

