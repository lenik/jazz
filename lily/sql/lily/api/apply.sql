--\import lily.account

    create sequence apply_seq start with 1000;
    create table apply(
        id          int primary key default nextval('apply_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.ExVer
--\mixin lily.mixin.Code
--\mixin lily.mixin.Label
--\mixin lily.mixin.MiMsg
        dummy       int
    );

    create index apply_label            on apply(label);
    create index apply_subject          on apply(subject);
