--\import lily.base._schema

    create sequence _form_seq start with 10000;

    create table _form(
        id          int primary key default nextval('_form_seq'),
--\mixin lily.mixin.Code
--\mixin lily.mixin.LabelExVer

        schema      int not null
            references _schema on update cascade on delete cascade,

        subject     varchar(200),
        text        text
    );

    create index _form_label          on _form(label);
    create index _form_lastmod        on _form(lastmod desc);
    create index _form_priority       on _form(priority);
    create index _form_state          on _form(state);
