--\import lily.account

    create sequence apitype_seq start with 1000;
    create table apitype(
        id          int primary key default nextval('apitype_seq'),
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Code

        -- Properties:
        --   callback: template...
--\mixin lily.mixin.PropFiles
        
        uom         varchar(30) not null default 'times'
    );

    create index apitype_label          on apitype(label);
