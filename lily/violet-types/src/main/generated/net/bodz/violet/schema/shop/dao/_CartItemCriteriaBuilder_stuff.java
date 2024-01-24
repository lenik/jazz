package net.bodz.violet.schema.shop.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _CartItemCriteriaBuilder_stuff<self_t extends _CartItemCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final LongField shopItemId = _long("shopitem");

    public final BigDecimalField price = bigDecimal("price");

    public final BigDecimalField quantity = bigDecimal("qty");

}
