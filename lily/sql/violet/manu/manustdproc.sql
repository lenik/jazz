--\import lily.account
--\import violet.art.artmodel
--\import violet.manu.manufn
--\import violet.manu.manustdtest

    create sequence manustdproc_seq start with 1000;
    create table manustdproc(
        id          int primary key default nextval('manustdproc_seq'),
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
            references manufn(id) on update cascade,
        
        -- expected consume time, in seconds
        -- avg. wage in minute: cpst.labor / duration * 60
        duration    int not null default 0,
        
        -- strict test, testing is required.
        strict      boolean not null default false,
        test        int
            references manustdtest(id) on update cascade
    );

    create index manustdproc_label       on manustdproc(label);
    create index manustdproc_lastmod     on manustdproc(lastmod desc);
    create index manustdproc_priority    on manustdproc(priority);
    create index manustdproc_state       on manustdproc(state);
    create index manustdproc_uid_acl     on manustdproc(uid, acl);
