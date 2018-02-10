    create sequence policy_seq start with 1000;
    create table policy(
        id          int primary key default nextval('policy_seq'),
--\mixin lily.mixin.Code
--\mixin lily.mixin.Label
        
        class       varchar(80) not null,
        method      varchar(40) null,
        allow       int not null default 0,
        deny        int not null default 0
    );

    create index policy_code        on policy(code);
    create index policy_label       on policy(label);
