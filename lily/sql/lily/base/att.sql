--\import lily.base.schema

    create sequence att_seq start with 10000;

    create table att(
        id          int primary key default nextval('att_seq'),
--\mixin lily.mixin.Code
--\mixin lily.mixin.LabelExVer

        schema      int not null
            references schema(id) on update cascade on delete cascade
    );
