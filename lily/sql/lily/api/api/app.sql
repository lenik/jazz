--\import lily.account

    create sequence app_seq start with 1000;
    create table app(
        id          int primary key default nextval('app_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Code
--\mixin lily.mixin.Props
        key
    );

    create index app_label          on app(label);
    create index app_depth          on app(depth);
    create index app_postcode       on app(postcode);
