--\import lily.account
--\import lily.util.uom
--\import violet.art.artcat
--\import violet.art.artphase

    create sequence art_seq start with 1000;
    create table art(
        id          int primary key default nextval('art_seq'),
        sku         varchar(30),    -- aka. code-name
        barcode     varchar(30),    -- res. for EAN-13 etc.
        rfid        varchar(30),    -- res. for 96-bit epc

--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer

        -- model name, detail part from the label.
        model       varchar(80),

        -- the initial structure is copied from the proto type.
        -- null proto for system type...
        proto       int
            references art(id) on update cascade,

        cat         int
            references artcat(id) on update cascade,

        phase       int
            references artphase(id) on update cascade,

        uom         int
            references uom(id) on update cascade,

--\mixin lily.mixin.Props
        -- color
        -- supply.delay (days)
        -- weight.gross
        -- weight.net
        -- bbox = [ dx, dy, dz ]
        
        -- calculate the default expire.
        -- life.shelf   保质期
        -- life.fresh   保鲜期
        -- life.running 打开后保质期

    -- props:
        -- bom: bill of materials
        -- length(the longest process chain in the bom graph), 0 for raw material.
        
        -- 0: raw material, large number for finished-product.
        -- For plants, negative number for growing-days.
        finish      smallint not null default 0,
        
        -- sell price (cache)
        price       decimal(12, 2)
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
