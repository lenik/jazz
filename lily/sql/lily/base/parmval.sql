--\import lily.base.parm

    create sequence parmval_seq start with 10000;
    
    create table parmval(
        id          int primary key default nextval('parmval_seq'),
--\mixin lily.mixin.Code
--\mixin lily.mixin.LabelExVer

        parm       int not null
            references parm(id) on update cascade on delete cascade,

        val         text not null
    );

    create index parmval_label     on parmval(label);
    create index parmval_priority  on parmval(priority);
