--\import lily.account
--\import violet.art.artmodel
--\import violet.fab.fabfn
--\import violet.fab.fabstdtest

    create sequence fabstdproc_seq start with 1000;
    create table fabstdproc(
        id          int primary key default nextval('fabstdproc_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
        -- priority: "step order"
--\mixin lily.mixin.ValidControl
--\mixin lily.mixin.Props
        -- cost: { labor, elec, equip, other }
        -- help: { how to use equipment, how to operate, ... }
        output      int not null
            references artmodel(id) on update cascade on delete cascade,
        
        -- "step name"
        -- null if transient process, nothing to do.
        fn          int
            references fabfn(id) on update cascade,
        
        -- expected consume time, in seconds
        -- avg. wage in minute: cpst.labor / duration * 60
        duration    int not null default 0,
        
        -- strict test, testing is required.
        strict      boolean not null default false,
        test        int
            references fabstdtest(id) on update cascade
    );

    create index fabstdproc_label       on fabstdproc(label);
    create index fabstdproc_lastmod     on fabstdproc(lastmod desc);
    create index fabstdproc_priority    on fabstdproc(priority);
    create index fabstdproc_state       on fabstdproc(state);
    create index fabstdproc_uid_acl     on fabstdproc(uid, acl);
