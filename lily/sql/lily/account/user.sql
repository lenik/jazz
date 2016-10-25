--\import lily.account.group

    create sequence user_seq start with 1000;
    create table "user"(
        id          int primary key default nextval('user_seq'),
        name        varchar(32) not null unique,
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Props

        gid0        int             -- primary gid, 0 for root user.
            references "group"(id) on update cascade on delete set null,
        
        referer     int
            references "user"(id) on update cascade on delete set null
    );

    create index user_label         on "user"(label);
    create index user_lastmod       on "user"(lastmod desc);
    create index user_state         on "user"(state);
  --create index user_props         on "user" using gin(props);

--\import lily.account.userid
--\import lily.account.userof
--\import lily.account.usersec

    insert into "group"(id, name, label) values(0, 'root', 'Root');
    insert into "user"(id, gid0, name, label) values(0, 0, 'root', 'Root');
    insert into userof("user", "group") values(0, 0);

        -- the admin user for this group.
        -- by default it should be the creator.
    alter table "group" add admin int not null default 0
            references "user"(id) on update cascade;

    insert into usersec("user", passwd) values(0, 'root');

