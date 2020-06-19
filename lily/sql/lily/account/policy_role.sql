--\import lily.account.policy
--\import lily.account.role

    create table policy_role(
--\mixin lily.mixin.Ver
        policy  int not null
            references policy on update cascade on delete cascade,

        "role"  int not null
            references "role" on update cascade on delete cascade,
        
        -- timestamp of the membership

        primary key(policy, "role")
    );
