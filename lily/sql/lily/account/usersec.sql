--\import lily.account.user

    create table usersec(
        --\mixin lily.mixin.Ver
        "user"      int primary key,

        salt        int not null default random() * 1000000000,
        passwd      varchar(40) not null default '',

        pubkey      varchar(250),

        -- some users don't have an email, like admin, guest.
        email       varchar(40),
        emailok     boolean not null default false,

        tel         varchar(30),
        telok       boolean not null default false,

        --\mixin lily.mixin.Props
        -- alt.email
        -- alt.tel
        -- fingerprint

        ques        varchar(100),
        ans         varchar(30)
    );

    create index usersec_lastmod    on "usersec"(lastmod desc);
