--\import lily.account
--\import lily.vapp.vapp
--\import lily.vapp.apitype

    create sequence vapi_log_seq start with 1000;
    create table vapi_log(
        id          bigint primary key default nextval('vapi_log_seq'),
--\mixin lily.mixin.Label

        -- priority: verbose level(ERROR..DEBUG)
--\mixin lily.mixin.Ex

--\mixin lily.mixin.Ver
        
        app         int not null
            references vapp on update cascade,
        
        api         int
            references apitype on update cascade,
        
        message     text,       -- full message
        err         text        -- exception dump
    );
    
    create index vapi_log_label          on vapi_log(label);

