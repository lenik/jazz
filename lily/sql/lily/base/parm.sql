--\import lily.base.schema

    create sequence parm_seq start with 10000;

    create table parm(
        id          int primary key default nextval('parm_seq'),
--\mixin lily.mixin.Code
--\mixin lily.mixin.LabelExVer

        schema      int not null
            references schema(id) on update cascade on delete cascade
    );
