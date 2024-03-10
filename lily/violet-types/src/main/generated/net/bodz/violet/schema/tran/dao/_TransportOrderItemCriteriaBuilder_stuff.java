package net.bodz.violet.schema.tran.dao;

import net.bodz.lily.concrete.CoEventCriteriaBuilder;

public class _TransportOrderItemCriteriaBuilder_stuff<self_t extends _TransportOrderItemCriteriaBuilder_stuff<self_t>>
        extends CoEventCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final LongField orderId = _long("odr");

    public final IntegerField artifactId = integer("art");

    public final BigDecimalField quantity = bigDecimal("qty");

    public final BigDecimalField price = bigDecimal("price");

    public final BigDecimalField amount = bigDecimal("amount");

}
