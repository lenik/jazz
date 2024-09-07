--\import lily.account.user

    create sequence usersec_seq start with 1000;
    create table usersec(
        id          int primary key default nextval('usersec_seq'),
--\mixin lily.mixin.Ver

        -- fingerprint
        -- cache:
        --      pubkey, email, mobile/tel
--\mixin lily.mixin.Props

        "user"      int not null
            references "user" on update cascade on delete cascade,

        passwd      varchar(40) not null default '',

        question    varchar(100),
        answer      varchar(30)
    );

    create index usersec_lastmod        on usersec(lastmod desc);
    create index usersec_passwd         on usersec(passwd);

    comment on table usersec is 'User Secret';
    comment on column usersec."user" is 'The declaring user';
    comment on column usersec.passwd is 'Password data';
    comment on column usersec.question is 'Protection question';
    comment on column usersec.answer is 'Protection answer';
