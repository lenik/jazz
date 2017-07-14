--\import lily.account.user
--\import lily.gaming.badge

    create sequence user_badge_seq start with 10000;

    create table user_badge(
        id          int primary key default nextval('user_badge_seq'),
--\mixin lily.mixin.Ver

        "user"      int
            references "user"(id) on update cascade on delete cascade,
        badge       int
            references badge(id) on update cascade on delete cascade
    );

    create index user_badge_lastmod      on user_badge(lastmod desc);
    create index user_badge_version      on user_badge(version);

