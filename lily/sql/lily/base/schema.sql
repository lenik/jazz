    create sequence schema_seq start with 100;

    create table schema(
        id          int primary key default nextval('schema_seq'),
--\mixin lily.mixin.Code
--\mixin lily.mixin.LabelExVer
        dummy       int
    );
