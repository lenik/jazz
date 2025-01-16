
    -- Column-Group: Ver
        creation    timestamp with time zone not null default now(),    -- Creation Time
        lastmod     timestamp with time zone not null default now(),    -- Modified Time
        version     int not null default 0,
