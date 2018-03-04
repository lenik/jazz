--\import lily.account
--\import violet.art.art

    -- official price, and price history.
    
    create sequence discount_seq start with 1000;
    create table discount(
        id          int primary key default nextval('discount_seq'),
        code        varchar(20) unique, -- not null?
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.Ver

        -- filters
--\mixin lily.mixin.Mi
        
        art         int
            references art(id) on update cascade on delete cascade,
        artcat      int
            references artcat(id) on update cascade on delete cascade,

        "user"      int
            references "user"(id) on update cascade on delete cascade,
        "group"     int
            references "group"(id) on update cascade on delete cascade,
        
        sscat       int
            references subscrcat(id) on update cascade on delete cascade,
        
        coucat       int
            references couponcat(id) on update cascade on delete cascade,
        
        min_val     decimal(12, 2),
        min_qty     decimal(12, 2),
        min_nkind   int,
        min_nshop   int,
        
        -- alg.
        cutoff      decimal(12, 2) not null default 0,
        cutoffs     decimal(12, 2) not null default 0,
        rate        decimal(12, 2) not null default 100,
        shipfree    boolean not null default false
    );

    create index discount_lastmod     on discount(lastmod desc);
    create index discount_uid_acl     on discount(uid, acl);
