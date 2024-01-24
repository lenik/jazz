package net.bodz.violet.schema.shop.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _SalesOrderItemCriteriaBuilder_stuff<self_t extends _SalesOrderItemCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final IntegerField year = integer("\"year\"");

    public final LongField orderId = _long("odr");

    public final LongField shopItemId = _long("shopitem");

    public final IntegerField artifactId = integer("art");

    public final BigDecimalField quantity = bigDecimal("qty");

    public final BigDecimalField price = bigDecimal("price");

    public final BigDecimalField amount = bigDecimal("amount");

    public final BigDecimalField n1 = bigDecimal("n1");

}
