--\import lily.account

    create sequence api_seq start with 1000;
    create table api(
        id          int primary key default nextval('api_seq'),
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Code
--\mixin lily.mixin.Props
        
        uom         varchar(30) not null default 'times'
    );

    create index api_label          on api(label);
