--\import lily.account.role
--\import lily.account.group

    create table role_group(
        "role" int not null
            references "role" on update cascade on delete cascade,
        
        "group" int not null
            references "group" on update cascade on delete cascade,
        
--\mixin lily.mixin.Label
        -- a descriptive role name.

--\mixin lily.mixin.Ex
        -- state: INIT=NORMAL, REQUEST

--\mixin lily.mixin.Ver
        -- timestamp of the membership

        primary key("role", "group")
    );
    
    insert into role_group("role", "group") values(0, 0);
