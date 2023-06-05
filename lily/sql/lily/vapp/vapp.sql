--\import lily.account
--\import lily.vapp.vappreq
--\import lily.vapp.vappcat

    create sequence vapp_seq start with 1000;
    create table vapp(
        id          int primary key default nextval('vapp_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Code
--\mixin lily.mixin.Props
        
        -- (optional)
        req         int
            references vappreq on update cascade on delete set null,

        cat         int
            references vappcat on update cascade,

        -- can be public key.
        secret      text not null
    );

    create index vapp_label          on vapp(label);
