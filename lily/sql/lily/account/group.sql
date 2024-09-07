--\import lily.account.grouptype

    create sequence group_seq start with 1000;

    create table "group"(
        -- 0 for root group
        id          int primary key default nextval('group_seq'),
        name        varchar(32) not null unique,
        type        int not null
            references grouptype(id) on update cascade,

        --\mixin lily.mixin.LabelExVer
        --\mixin lily.mixin.Props
        
        parent      int
            references group(id) on update cascade
    );

    create index group_label        on "group"(label);
    create index group_lastmod      on "group"(lastmod desc);
    create index group_state        on "group"(state);
    
    insert into "group"(id, name, label) values(0, 'root', 'Root');
    insert into "group"(id, name, label) values(1, 'admin', 'Administrators');
    insert into "group"(id, name, label) values(2, 'user', 'Normal Users');
    insert into "group"(id, name, label) values(3, 'guest', 'Guest Users');

