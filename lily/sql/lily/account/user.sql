--\import lily.account.group
--\import lily.account.usertype

    create sequence user_seq start with 1000;
    create table "user"(
        id          int primary key default nextval('user_seq'),
        type        int not null default 0
            references usertype(id) on update cascade,
        name        varchar(32) not null unique,
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Props

        gid0        int not null default 2
            references "group" on update cascade,
        referer     int
            references "user" on update cascade on delete set null
    );

    create index user_label         on "user"(label);
    create index user_lastmod       on "user"(lastmod desc);
    create index user_state         on "user"(state);
  --create index user_props         on "user" using gin(props);

    comment on table "user" is 'User Account';
    comment on column "user".name is 'The user name (unique)';
    comment on column "user".type is 'User type like system-user, normal-user, etc.';
    comment on column "user".gid0 is 'The primary user group, the default value of ownerGroup.';
    comment on column "user".referer is 'The referer user (used for promotion)';

    insert into "user"(id, name, label, gid0) values(0, 'root', 'Root', 0);
    insert into "user"(id, name, label, gid0) values(1, 'admin', 'Administrator', 101);
    insert into "user"(id, name, label, gid0) values(2, 'user', 'Default User', 102);
    insert into "user"(id, name, label, gid0) values(3, 'guest', 'Guest', 103);

--\import lily.account.usersec
    insert into usersec(id, "user", passwd) values(0, 0, 'toor');
    insert into usersec(id, "user", passwd) values(1, 1, 'nimda');
    insert into usersec(id, "user", passwd) values(2, 2, 'resu');
    insert into usersec(id, "user", passwd) values(3, 3, 'guest');
