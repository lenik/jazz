--\import violet.art.art

    create sequence offstorel_seq start with 1000;
    create table offstorel(
        id          bigint primary key default nextval('offstorel_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.Ex
--\mixin lily.mixin.Ver

        -- DEPRECATED: Different to storel, (art, batch*) can be repeated.
        art         int not null
            references art(id) on update cascade,

--\mixin violet.store._batch

        qty         numeric(20,2) not null
    );

    create index offstorel_lastmod  on offstorel(lastmod desc);
    create index offstorel_priority on offstorel(priority);
    create index offstorel_state    on offstorel(state);
    create index offstorel_uid_acl  on offstorel(uid, acl);
  --create index offstorel_uom      on offstorel(uom);

