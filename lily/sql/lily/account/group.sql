--\import lily.account.grouptype

    create sequence group_seq start with 1000;

    create table "group"(
        -- 0 for root group
        id          int primary key default nextval('group_seq'),
        name        varchar(32) not null unique,
        type        int not null default 2
            references grouptype(id) on update cascade,

        --\mixin lily.mixin.LabelExVer
        --\mixin lily.mixin.PropFiles
        
        parent      int
            references "group"(id) on update cascade
    );

    create index group_label        on "group"(label);
    create index group_lastmod      on "group"(lastmod desc);
    create index group_state        on "group"(state);

    comment on table "group" is 'User Group';
    comment on column "group".name is 'Name: The group name (unique)';
    comment on column "group".type is 'Type: Group type like normal-group, role-group, etc.';
    comment on column "group".parent is 'Parent Group: The parent group. must be acyclic';

    insert into "group"
        (       id,     type, name,     icon,           label) values
        (       1,      0,  '_pseudo',  'fab-adn',      'Pseudo Root Node');
    insert into "group"
        (parent, id,    type, name,     icon,           label) values
        (1,     100,    0,  '_sysgroup','fa-cubes',     'System Groups'),
        (1,     200,    0,  '_group',   'fa-cubes',     'Normal Groups'),
        (1,     300,    0,  '_role',    'fa-cubes',     'Role parent, Groups'),
        (100,   0,      1,  'root',     'far-users-cog','Root'),
        (100,   101,    1,  'admin',    'far-users-cog','Administrators'),
        (100,   102,    1,  'user',     'far-users',    'Normal Users'),
        (100,   103,    1,  'guest',    'far-male',     'Guest Users');
