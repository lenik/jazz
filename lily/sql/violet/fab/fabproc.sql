--\import violet.art.artmodel
--\import violet.fab.fabtask
--\import violet.fab.fabstdproc

    create sequence fabproc_seq start with 1000;
    create table fabproc(
        id          bigint primary key default nextval('fabproc_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Mi
        -- t0: actual start date
        -- t1: actual finish date

        task        bigint not null
            references fabtask(id) on update cascade on delete cascade,
        
        -- so forms the full process tree.
        parent      bigint
            references fabproc(id) on update cascade,
        
        -- (intermediate) output, maybe different to the task.
        output      int not null
            references artmodel(id) on update cascade,
        
        -- std := fabstdproc[output == proc.output]
        std         int not null
            references fabstdproc(id) on update cascade,
        
        qty         numeric(20, 2) not null,
        
--\mixin violet.store._batch
        -- see also fabproc_sn*
        
        since       timestamp with time zone not null,  -- schedule start date
        deadline    timestamp with time zone not null,  -- schedule end date
        ntrack      int         -- cache
    );

    create index fabproc_label       on fabproc(label);
    create index fabproc_lastmod     on fabproc(lastmod desc);
    create index fabproc_priority    on fabproc(priority);
    create index fabproc_state       on fabproc(state);
    create index fabproc_uid_acl     on fabproc(uid, acl);
