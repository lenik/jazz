--\import lily.account
--\import lily.api.app
--\import lily.api.api
--\import lily.api.appacntcat

    create sequence appacnt_seq start with 1000;
    create table appacnt(
        id          int primary key default nextval('appacnt_seq'),
--\mixin lily.mixin.ExVer
--\mixin lily.mixin.Mi
        
        app         int not null
            references app on update cascade,
        
        api         int not null
            references api on update cascade,
        
        cat         int not null
            references appacntcat on update cascade,
        
        qty         numeric(20, 4) not null
    );
