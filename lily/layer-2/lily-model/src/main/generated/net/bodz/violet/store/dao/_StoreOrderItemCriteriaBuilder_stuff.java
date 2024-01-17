package net.bodz.violet.store.dao;

import net.bodz.lily.model.base.CoMomentIntervalCriteriaBuilder;

public class _StoreOrderItemCriteriaBuilder_stuff<self_t extends _StoreOrderItemCriteriaBuilder_stuff<self_t>>
        extends CoMomentIntervalCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final LongField orderId = _long("odr");

    public final IntegerField artifactId = integer("art");

    public final IntegerField regionId = integer("region");

    public final LongField serial = _long("serial");

    public final BigDecimalField quantity = bigDecimal("qty");

    public final BigDecimalField price = bigDecimal("price");

    public final BigDecimalField amount = bigDecimal("amount");

    public final StringField notes = string("notes");

}
