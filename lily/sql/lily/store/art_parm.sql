--\import lily.account
--\import lily.store.art
--\import lily.store.artparm

    -- art parameters
    
    create sequence art_parm_seq start with 1000;
    create table art_parm(
        id          int primary key default nextval('art_parm_seq'),
--\mixin lily.mixin.LabelExVer

        art         int
            references art(id) on update cascade on delete cascade,

        parm        int
            references artparm(id) on update cascade on delete cascade,

        ival        int,
        fval        double precision,
        sval        varchar(255)
    );

    create index art_parm_label          on art_parm(label);
    create index art_parm_lastmod        on art_parm(lastmod desc);
