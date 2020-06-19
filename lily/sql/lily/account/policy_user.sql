--\import lily.account.policy
--\import lily.account.user

    create table policy_user(
--\mixin lily.mixin.Ver
        policy  int not null
            references policy on update cascade on delete cascade,

        "user"  int not null
            references "user" on update cascade on delete cascade,
        
        -- timestamp of the membership

        primary key(policy, "user")
    );
