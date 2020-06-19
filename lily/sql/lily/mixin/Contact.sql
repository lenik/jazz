
    -- Column-Group: Contact
        $1alias       varchar(32),
        $1ctprops     jsonb,          -- contact fields

        $1address1    varchar(80) not null default '',
        $1address2    varchar(80) not null default '',
        $1zone        int
            references zone on update cascade,

        $1tel         varchar(20),    -- the primary tel
        $1telok       boolean not null default false,

        $1email       varchar(30),    -- the primary email
        $1emailok     boolean not null default false,
