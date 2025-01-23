--\import lily.account
--\import lily.vapp.apitype
--\import lily.vapp.vapp

    create sequence vapi_seq start with 1000;
    create table vapi(
        id          bigint primary key default nextval('vapi_seq'),
--\mixin lily.mixin.Ver
        
        -- Properties:
        --   
--\mixin lily.mixin.PropFiles
        
        app         int not null
            references vapp on update cascade on delete cascade,
        
        api         int not null
            references apitype on update cascade,
        
        -- mode        int not null default -1
        
        callback    varchar(200)
    );
