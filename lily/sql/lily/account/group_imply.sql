--\import lily.account.group

    create table group_imply(
        "group" int not null
            references "group" on update cascade on delete cascade,

        "implied" int not null
            references "group" on update cascade on delete cascade,
        
--\mixin lily.mixin.Ex
        -- state: INIT=NORMAL, REQUEST

--\mixin lily.mixin.Ver
        -- timestamp of the membership

        primary key("group", "implied")
    );
    