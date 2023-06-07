    create sequence policy_seq start with 1000;
    create table policy(
        id          int primary key default nextval('policy_seq'),
--\mixin lily.mixin.Name
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Props
        
        cclass       varchar(80) not null,
        method      varchar(80) null,
        allow       int not null default 0,
        deny        int not null default 0
    );

    create index policy_name        on policy(name);
    create index policy_label       on policy(label);

    comment on table policy is 'Security Policy';
    comment on column policy.name is 'The policy name (unique)';
    comment on column policy.cclass is 'The control class';
    comment on column policy.method is 'The method name';
    comment on column policy.allow is 'allow';
    comment on column policy.deny is 'deny';
