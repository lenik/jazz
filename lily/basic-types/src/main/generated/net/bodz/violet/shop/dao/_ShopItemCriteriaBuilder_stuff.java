package net.bodz.violet.shop.dao;

import net.bodz.lily.model.base.CoMomentIntervalCriteriaBuilder;

public class _ShopItemCriteriaBuilder_stuff<self_t extends _ShopItemCriteriaBuilder_stuff<self_t>>
        extends CoMomentIntervalCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final IntegerField shopId = integer("shop");

    public final IntegerField categoryId = integer("cat");

    public final IntegerField artifactId = integer("art");

    public final BigDecimalField price = bigDecimal("price");

    public final BigDecimalField quantity = bigDecimal("qty");

}
