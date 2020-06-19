--\import lily.account
--\import lily.api.app
--\import lily.api.api

    create sequence appcred_seq start with 1000;
    create table appcred(
        id          int primary key default nextval('appcred_seq'),
--\mixin lily.mixin.ExVer
        
        app         int not null
            references app on update cascade,
        
        api         int not null
            references api on update cascade,
        
        credit      numeric(20, 4) not null
    );
