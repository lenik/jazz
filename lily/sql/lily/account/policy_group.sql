--\import lily.account.policy
--\import lily.account.group

    create table policy_group(
--\mixin lily.mixin.Ver
        policy  int not null
            references policy(id) on update cascade on delete cascade,

        "group"  int not null
            references "group"(id) on update cascade on delete cascade,
        
        -- timestamp of the membership

        primary key(policy, "group")
    );
