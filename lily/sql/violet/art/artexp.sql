--\import violet.art.art

    -- art expending, consumable parts.
    create sequence artexp_seq start with 1000;
    create table artexp(
        id          int primary key default nextval('artexp_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.Ver

        main        int not null
            references art(id) on update cascade,
            
        sub         int not null
            references art(id) on update cascade,
        
        -- expendable, spare parts, consumables, replacements, accessories
        type        int not null,
        
        -- uom in sub art.
        qty         int not null default 1
    );

    -- create index artexp_type        on artexp(type);
