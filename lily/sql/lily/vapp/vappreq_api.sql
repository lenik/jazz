--\import lily.account
--\import lily.vapp.apitype
--\import lily.vapp.vappreq

    create sequence vappreq_api_seq start with 1000;
    create table vappreq_api(
        id          bigint primary key default nextval('vappreq_api_seq'),
--\mixin lily.mixin.Ex
--\mixin lily.mixin.Ver
        
        parent      int not null
            references vappreq on update cascade on delete cascade,
        
        api         int not null
            references apitype on update cascade
    );
