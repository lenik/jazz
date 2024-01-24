package net.bodz.violet.schema.store.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _StoreItemCriteriaBuilder_stuff<self_t extends _StoreItemCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField artifactId = integer("art");

    public final IntegerField regionId = integer("region");

    public final BigDecimalField quantity = bigDecimal("qty");

}
