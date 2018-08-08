--\import lily.account
--\import lily.api.apply
--\import lily.api.api

    create sequence apply_api_seq start with 1000;
    create table apply_api(
        id          bigint primary key default nextval('apply_api_seq'),
--\mixin lily.mixin.Ex
--\mixin lily.mixin.Ver
        
        apply       int not null
            references apply(id) on update cascade,
        
        api         int not null
            references api(id) on update cascade
    );
