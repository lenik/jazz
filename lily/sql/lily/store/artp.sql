--\import lily.account
--\import lily.store.art
--\import lily.store.artpdef

    -- art parameters
    
    create sequence artp_seq start with 1000;
    create table artp(
        id          int primary key default nextval('artp_seq'),
--\mixin lily.mixin.LabelExVer

        art         int
            references art(id) on update cascade on delete cascade,

        param       int
            references artpdef(id) on update cascade on delete cascade,

        ival        int,
        fval        double precision,
        sval        varchar(255)
    );

    create index artp_label          on artp(label);
    create index artp_lastmod        on artp(lastmod desc);
