
    create sequence usertype_seq start with 1000;
    create table usertype(
        id          int primary key default nextval('usertype_seq'),
        name        varchar(20) unique,
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Props
        dummy       int
    );

    create index usertype_label        on usertype(label);
    create index usertype_lastmod      on usertype(lastmod desc);
    create index usertype_priority     on usertype(priority);
    create index usertype_state        on usertype(state);

    comment on table usertype is 'User Type';
    comment on column usertype.name is 'The user type name';

    insert into usertype(id, name, label, description) values
        (0, 'sys', 'System User', 'System user'),
        (1, 'normal', 'Normal User', 'Normal user (default)');
