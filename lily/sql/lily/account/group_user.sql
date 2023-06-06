-- This file must be separated from user/group.
-- Because SQL doesn't support forward-declaration.
--\import lily.account.user
--\import lily.account.group

    create table group_user(
        "group" int not null
            references "group" on update cascade on delete cascade,

        "user"  int not null
            references "user" on update cascade on delete cascade,
        
        admin   boolean not null default false,

--\mixin lily.mixin.Label
        -- a descriptive role name.

--\mixin lily.mixin.Ex
        -- state: INIT=NORMAL, REQUEST

--\mixin lily.mixin.Ver
        -- timestamp of the membership

        primary key("group", "user")
    );
    
    -- primary gid, 0 for root user.
    -- alter table "user" alter gid0 drop default;
    
    comment on table group_user is 'Group member user';
    comment on column group_user."group" is 'The containing group';
    comment on column group_user."user" is 'A user who is a member to the group';
    comment on column group_user.admin is 'Whether the member is an administrator of the group';

    -- root: root
    insert into group_user("user", "group") values(0, 0);

    -- admin: admin, user
    insert into group_user("user", "group") values(1, 101);
    insert into group_user("user", "group") values(1, 102);

    -- user: user
    insert into group_user("user", "group") values(2, 102);

    -- guest: guest
    insert into group_user("user", "group") values(3, 103);
