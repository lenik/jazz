--\import lily.account
--\import lily.util.uom
--\import lily.store.artcat

    create sequence art_seq start with 1000;
    create table art(
        id          int primary key default nextval('art_seq'),
        sku         varchar(30),
        barcode     varchar(30),

--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer

        -- null proto for system type...
        proto       int
            references art(id) on update cascade,

        cat         int
            references artcat(id) on update cascade,

        --   0- 99: raw-material, purchased
        -- 100-199: semi-product
        -- 200-299: product
        level       int not null default 0,

        model       varchar(100),
--\mixin lily.mixin.Rgba

        -- See also props: qty.property, qty.digits
        uom         varchar(10),

--\mixin lily.mixin.Props
        -- supply.delay (days)
        -- weight.gross
        -- weight.net
        -- bbox = [ dx, dy, dz ]
        
        -- calculate the default expire.
        -- life.shelf   保质期
        -- life.fresh   保鲜期

        bom         jsonb
    );

    create index art_label          on art(label);
    create index art_lastmod        on art(lastmod desc);
    create index art_model          on art(model);
    create index art_priority       on art(priority);
    create index art_state          on art(state);
    create index art_uid_acl        on art(uid, acl);
  --create index art_uom            on art(uom);

-- Predefined prototypes
    insert into art(id, label) values(1, 'Abstract');
    insert into art(id, label) values(2, 'Produced');
    insert into art(id, label) values(3, 'Purchased');
