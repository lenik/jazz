--\import lily.account.group

    create sequence user_seq start with 1000;
    create table "user"(
        id          int primary key default nextval('user_seq'),
        name        varchar(32) not null unique,
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Props

        referer     int
            references "user"(id) on update cascade on delete set null
    );

    create index user_label         on "user"(label);
    create index user_lastmod       on "user"(lastmod desc);
    create index user_state         on "user"(state);
  --create index user_props         on "user" using gin(props);

    insert into "user"(id, name, label) values(0, 'root', 'Root');

--\import lily.account.usersec
    insert into usersec(id, "user", passwd) values(0, 0, 'root');
