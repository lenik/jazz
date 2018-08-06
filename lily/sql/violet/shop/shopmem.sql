--\import lily.account
--\mixin lily.template.a-cat shopmem

    -- Membership shopmemiption
    
    create sequence shopmem_seq start with 1000;
    create table shopmem(
        id          int primary key default nextval('shopmem_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.Ver
--\mixin lily.mixin.Mi

        cat         int not null
            references shopmemcat(id) on update cascade on delete cascade,

        "user"      int not null
            references "user"(id) on update cascade on delete cascade
    );

    create index shopmem_lastmod     on shopmem(lastmod desc);
    create index shopmem_uid_acl     on shopmem(uid, acl);
