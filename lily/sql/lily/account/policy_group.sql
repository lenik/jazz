--\import lily.account.policy
--\import lily.account.group

    create table policy_group(
--\mixin lily.mixin.Ver
        policy  int not null
            references policy on update cascade on delete cascade,

        "group"  int not null
            references "group" on update cascade on delete cascade,
        
        -- timestamp of the membership

        primary key(policy, "group")
    );

    comment on table policy_group is 'Enabled policy for a group';
    comment on column policy_group.policy is 'Policy: The policy to be enabled';
    comment on column policy_group."group" is 'Group Applied: The group on which the policy will be applied';
