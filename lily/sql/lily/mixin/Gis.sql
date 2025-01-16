
    -- Column-Group: Gis
        lng         float,  -- Longitude: Longitude/X
        lat         float,  -- Latitude: Latitude/Y
        _zone       int     -- Default Zone: if any (cache)
            references zone on update cascade,

