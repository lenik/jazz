--\import violet.art.artmodel
--\import violet.manu.manutask
--\import violet.manu.manustdproc

    create sequence manuproc_seq start with 1000;
    create table manuproc(
        id          bigint primary key default nextval('manuproc_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Mi
        -- t0: actual start date
        -- t1: actual finish date

        task        bigint not null
            references manutask(id) on update cascade on delete cascade,
        
        -- so forms the full process tree.
        parent      bigint
            references manuproc(id) on update cascade,
        
        -- (intermediate) output, maybe different to the task.
        output      int not null
            references artmodel(id) on update cascade,
        
        -- std := manustdproc[output == proc.output]
        std         int not null
            references manustdproc(id) on update cascade,
        
        qty         numeric(20, 2) not null,
        
--\mixin violet.store._batch
        -- see also manuproc_sn*
        
        since       timestamp with time zone not null,  -- schedule start date
        deadline    timestamp with time zone not null,  -- schedule end date
        ntrack      int         -- cache
    );

    create index manuproc_label       on manuproc(label);
    create index manuproc_lastmod     on manuproc(lastmod desc);
    create index manuproc_priority    on manuproc(priority);
    create index manuproc_state       on manuproc(state);
    create index manuproc_uid_acl     on manuproc(uid, acl);
