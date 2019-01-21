--\import lily.account
--\import lily.api.app
--\import lily.api.api

    create sequence app_api_seq start with 1000;
    create table app_api(
        id          bigint primary key default nextval('app_api_seq'),
--\mixin lily.mixin.Ver
        
        -- Properties:
        --   
--\mixin lily.mixin.Props
        
        app         int not null
            references app(id) on update cascade on delete cascade,
        
        api         int not null
            references api(id) on update cascade,
        
        -- mode        int not null default -1
        
        callback    varchar(200)
    );
