--\import lily.base._schema

    create sequence _priority_seq start with 10000;

    create table _priority(
        id          int primary key default nextval('_priority_seq'),
--\mixin lily.mixin.Code
--\mixin lily.mixin.LabelExVer
        schema      int not null
            references _schema on update cascade on delete cascade
    );

    create index _priority_label        on _priority(label);
    create index _priority_lastmod      on _priority(lastmod desc);
    create index _priority_priority     on _priority(priority);
    create index _priority_state        on _priority(state);
