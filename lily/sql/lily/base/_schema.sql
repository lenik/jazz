    create sequence _schema_seq start with 100;

    create table _schema(
        id          int primary key default nextval('_schema_seq'),
--\mixin lily.mixin.Code
--\mixin lily.mixin.LabelExVer
        dummy       int
    );
