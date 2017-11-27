--\import violet.shop.shop
--\import violet.store.art

-- Re-orderred category tree.
--\mixin lily.template.a-cat shopitem

-- storeodrl sum => shopitem
    create sequence shopitem_seq start with 1000;
    create table shopitem(
        id          bigint primary key default nextval('shopitem_seq'),
--\mixin lily.mixin.Label 200
--\mixin lily.mixin.Ex
--\mixin lily.mixin.Mi
            -- now < t0 for future items, now > t1 for expired items.
--\mixin lily.mixin.Ver
--\mixin lily.mixin.Props

        -- null shop if personal, or the only shop for uid.
        shop        int
            references shop(id) on update cascade,
            
        -- alternative cat to the art.cat.
        cat         int
            references shopitemcat(id) on update cascade,
        art         int not null
            references art(id) on update cascade,

--\mixin violet.store._batch

        price       numeric(20,2) not null default 0,
        
        -- reduncay. initial := sum(storeodrl.qty)
        qty         numeric(20,2) not null
    );

    create index shopitem_label         on shopitem(label);
    create index shopitem_lastmod       on shopitem(lastmod desc);
    create index shopitem_priority      on shopitem(priority);
    create index shopitem_state         on shopitem(state);

--\mixin lily.template.a-favs shopitem int label
