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

    comment on table policy_user is 'Enabled policy for a user';
    comment on column policy_user.policy is 'Policy: The policy to be enabled';
    comment on column policy_user."user" is 'User Applied: The user on which the policy will be applied';
