--\import lily.base._schema

    create sequence _parm_seq start with 10000;

    create table _parm(
        id          int primary key default nextval('_parm_seq'),
--\mixin lily.mixin.Code
--\mixin lily.mixin.LabelExVer

        schema      int not null
            references _schema(id) on update cascade on delete cascade
    );
