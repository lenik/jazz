--\import lily.base.schema

    create sequence form_seq start with 10000;

    create table form(
        id          int primary key default nextval('form_seq'),
--\mixin lily.mixin.Code
--\mixin lily.mixin.LabelExVer

        schema      int not null
            references schema(id) on update cascade on delete cascade,

        subject     varchar(200),
        text        text
    );

    create index form_label          on form(label);
    create index form_lastmod        on form(lastmod desc);
    create index form_priority       on form(priority);
    create index form_state          on form(state);
