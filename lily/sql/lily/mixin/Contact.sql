
        rename      varchar(32),

        address1    varchar(80) not null default '',
        address2    varchar(80) not null default '',
        zone        int
            references zone(id) on update cascade,

        tel         varchar(20),    -- the primary tel
        email       varchar(30),    -- the primary email

        ctprops     jsonb,          -- contact fields
