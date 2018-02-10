-- This file must be separated from user/group.
-- Because SQL doesn't support forward-declaration.
--\import lily.account.user
--\import lily.account.group

    create table group_user(
        "group" int not null
            references "group"(id) on update cascade on delete cascade,

        "user" int not null
            references "user"(id) on update cascade on delete cascade,
        
--\mixin lily.mixin.Label
        -- a descriptive role name.

--\mixin lily.mixin.Ex
        -- state: INIT=NORMAL, REQUEST

--\mixin lily.mixin.Ver
        -- timestamp of the membership

        primary key("group", "user")
    );
    
    -- the admin user for this group.
    -- by default it should be the creator.
    alter table "group" add admin int not null default 0
        references "user"(id) on update cascade on delete set null;
        
    -- primary gid, 0 for root user.
    alter table "user" add gid0 int not null default 0
        references "group"(id) on update cascade on delete set null;
    alter table "user" alter gid0 drop default;
    
    insert into group_user("group", "user") values(0, 0);
    
