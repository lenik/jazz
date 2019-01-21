--\import lily.account.group

    create sequence user_seq start with 1000;
    create table "user"(
        id          int primary key default nextval('user_seq'),
        name        varchar(32) not null unique,
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Props

        gid0        int not null default 2
            references "group"(id) on update cascade,
        referer     int
            references "user"(id) on update cascade on delete set null
    );

    create index user_label         on "user"(label);
    create index user_lastmod       on "user"(lastmod desc);
    create index user_state         on "user"(state);
  --create index user_props         on "user" using gin(props);

    insert into "user"(id, name, label, gid0) values(0, 'root', 'Root', 0);
    insert into "user"(id, name, label, gid0) values(1, 'admin', 'Administrator', 1);
    insert into "user"(id, name, label, gid0) values(2, 'user', 'Default User', 2);
    insert into "user"(id, name, label, gid0) values(3, 'guest', 'Guest', 3);

--\import lily.account.usersec
    insert into usersec(id, "user", passwd) values(0, 0, 'toor');
    insert into usersec(id, "user", passwd) values(1, 1, 'nimda');
    insert into usersec(id, "user", passwd) values(2, 2, 'resu');
    insert into usersec(id, "user", passwd) values(3, 3, 'guest');

