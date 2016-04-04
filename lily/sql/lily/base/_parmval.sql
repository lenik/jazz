--\import lily.base._parm

    create sequence _parmval_seq start with 10000;
    
    -- predefined parameter values
    create table _parmval(
        id          int primary key default nextval('_parmval_seq'),
--\mixin lily.mixin.Code
--\mixin lily.mixin.LabelExVer

        parm        int not null
            references _parm(id) on update cascade on delete cascade,

        val         text not null
    );

    create index _parmval_label     on _parmval(label);
    create index _parmval_priority  on _parmval(priority);
