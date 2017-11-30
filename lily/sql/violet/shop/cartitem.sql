--\import violet.shop.shopitem

    create sequence cartitem_seq start with 1000;
    create table cartitem(
        id          bigint primary key default nextval('cartitem_seq'),
--\mixin lily.mixin.Acl_rw-------
--\mixin lily.mixin.Label
--\mixin lily.mixin.Ex
--\mixin lily.mixin.Ver
        
        shopitem       bigint not null
            references shopitem(id) on update cascade,
        
        -- price snapshot
        price       numeric(20,2) not null,
        qty         numeric(20,2) not null
    );

    create index cartitem_label         on cartitem(label);
    create index cartitem_lastmod       on cartitem(lastmod desc);
    create index cartitem_priority      on cartitem(priority);
    create index cartitem_state         on cartitem(state);
