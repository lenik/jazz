
    create sequence grouptype_seq start with 1000;
    create table grouptype(
        id          int primary key default nextval('grouptype_seq'),
        name        varchar(20) unique, -- not null?
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Props
        dummy       int
    );

    create index grouptype_label        on grouptype(label);
    create index grouptype_lastmod      on grouptype(lastmod desc);
    create index grouptype_priority     on grouptype(priority);
    create index grouptype_state        on grouptype(state);

    insert into grouptype(id, name, label, description)
        values(0, 'VIRTUAL', 'Virtual Group', 'Virtual abstract group');
    insert into grouptype(id, name, label, description)
        values(1, 'SYS', 'System Group', 'System group');
    insert into grouptype(id, name, label, description)
        values(2, 'GROUP', 'Normal Group', 'Normal group (default)');
    insert into grouptype(id, name, label, description)
        values(3, 'ROLE', 'Role Group', 'Role group');
