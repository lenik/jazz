--\import lily.account.role
--\import lily.account.user

    create table role_user(
        "role" int not null
            references "role" on update cascade on delete cascade,
        
        "user" int not null
            references "user" on update cascade on delete cascade,
        
--\mixin lily.mixin.Label
        -- a descriptive role name.

--\mixin lily.mixin.Ex
        -- state: INIT=NORMAL, REQUEST

--\mixin lily.mixin.Ver
        -- timestamp of the membership

        primary key("role", "user")
    );
    
    insert into role_user("role", "user") values(0, 0);
