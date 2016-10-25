--\import lily.account.user
--\import lily.gaming.badge

    create sequence userbadge_seq start with 10000;

    create table userbadge(
        id          int primary key default nextval('userbadge_seq'),
--\mixin lily.mixin.Ver

        "user"      int
            references "user"(id) on update cascade on delete cascade,
        badge       int
            references badge(id) on update cascade on delete cascade
    );

    create index userbadge_lastmod      on userbadge(lastmod desc);
    create index userbadge_version      on userbadge(state);

