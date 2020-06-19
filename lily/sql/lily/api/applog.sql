--\import lily.account
--\import lily.api.app
--\import lily.api.api

    create sequence applog_seq start with 1000;
    create table applog(
        id          bigint primary key default nextval('applog_seq'),
--\mixin lily.mixin.Label
        -- priority: verbose level(ERROR..DEBUG)
--\mixin lily.mixin.Ex
--\mixin lily.mixin.Ver
        
        app         int not null
            references app on update cascade,
        
        api         int
            references api on update cascade,
        
        -- exception dump.
        err         text,
        
        -- detail message.
        message     text
    );
    
    create index applog_label          on applog(label);
