--\import lily.account

    create sequence fabstdtester_seq start with 1000;
    create table fabstdtester(
        id          int primary key default nextval('fabstdtester_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
        
        cmdline     varchar(200)
    );

    create index fabstdtester_label        on fabstdtester(label);
    create index fabstdtester_lastmod      on fabstdtester(lastmod desc);
    create index fabstdtester_priority     on fabstdtester(priority);
    create index fabstdtester_state        on fabstdtester(state);
    create index fabstdtester_uid_acl      on fabstdtester(uid, acl);
