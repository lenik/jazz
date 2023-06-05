--\import lily.account
--\import lily.vapp.apitype
--\import lily.vapp.vapp

    create sequence vapi_credit_seq start with 1000;
    create table vapi_credit(
        id          int primary key default nextval('vapi_credit_seq'),
--\mixin lily.mixin.ExVer
        
        app         int not null
            references vapp on update cascade,
        
        api         int not null
            references apitype on update cascade,
        
        credit      numeric(20, 4) not null
    );
