--\import lily.account

    create sequence manustdtester_seq start with 1000;
    create table manustdtester(
        id          int primary key default nextval('manustdtester_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
        
        cmdline     varchar(200)
    );

    create index manustdtester_label        on manustdtester(label);
    create index manustdtester_lastmod      on manustdtester(lastmod desc);
    create index manustdtester_priority     on manustdtester(priority);
    create index manustdtester_state        on manustdtester(state);
    create index manustdtester_uid_acl      on manustdtester(uid, acl);
