--\import lily.account
--\import lily.api.apply

    create sequence app_seq start with 1000;
    create table app(
        id          int primary key default nextval('app_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Code
--\mixin lily.mixin.Props
        
        -- (optional)
        apply       int
            references apply on update cascade,

        -- can be public key.
        secret      text not null
    );

    create index app_label          on app(label);
