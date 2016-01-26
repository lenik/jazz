-- This file must be separated from user/group.
-- Because SQL doesn't support forward-declaration.
--\import lily.account.user
--\import lily.account.group

    create table userof(
        "user" int not null
            references "user"(id) on update cascade on delete cascade,
        
        "group" int not null
            references "group"(id) on update cascade on delete cascade,

--\mixin lily.mixin.Label
        -- a descriptive role name.

--\mixin lily.mixin.Ex
        -- state: INIT=NORMAL, REQUEST

--\mixin lily.mixin.Ver
        -- timestamp of the membership

        constraint pkey primary key("user", "group")
    );

