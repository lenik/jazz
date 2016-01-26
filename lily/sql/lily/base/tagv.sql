--\import lily.base.schema

    create sequence tagv_seq start with 10000;

    create table tagv(
        id          int primary key default nextval('tagv_seq'),
--\mixin lily.mixin.Code
--\mixin lily.mixin.LabelExVer

        schema      int not null
            references schema(id) on update cascade on delete cascade,

        topic       boolean not null default false,
        reply       boolean not null default false
    );

    create index tagv_lastmod       on tagv(lastmod desc);
    create index tagv_priority      on tagv(priority);
    create index tagv_state         on tagv(state);
