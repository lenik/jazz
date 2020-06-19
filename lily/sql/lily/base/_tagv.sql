--\import lily.base._schema

    create sequence _tagv_seq start with 10000;

    create table _tagv(
        id          int primary key default nextval('_tagv_seq'),
--\mixin lily.mixin.Code
--\mixin lily.mixin.LabelExVer

        schema      int not null
            references _schema on update cascade on delete cascade,

        topic       boolean not null default false,
        reply       boolean not null default false
    );

    create index _tagv_lastmod       on _tagv(lastmod desc);
    create index _tagv_priority      on _tagv(priority);
    create index _tagv_state         on _tagv(state);
