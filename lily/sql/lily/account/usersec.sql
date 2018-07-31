--\import lily.account.user

    create sequence usersec_seq start with 1000;
    create table usersec(
        id          int primary key default nextval('usersec_seq'),
--\mixin lily.mixin.Ver

        "user"      int not null
            references "user"(id) on update cascade on delete cascade,

        salt        int not null default random() * 1000000000,
        passwd      varchar(40) not null default '',

        pubkey      varchar(250),

        -- some users don't have an email, like admin, guest.
        email       varchar(40),
        emailok     boolean not null default false,

        tel         varchar(20),
        telok       boolean not null default false,

--\mixin lily.mixin.Props
        -- alt.email
        -- alt.tel
        -- fingerprint

        ques        varchar(100),
        ans         varchar(30)
    );

    create index usersec_lastmod        on usersec(lastmod desc);
    create index usersec_passwd         on usersec(passwd);
    create index usersec_email          on usersec(email);
    create index usersec_tel            on usersec(tel);
