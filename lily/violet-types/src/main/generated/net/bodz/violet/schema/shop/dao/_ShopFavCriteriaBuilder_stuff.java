package net.bodz.violet.schema.shop.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _ShopFavCriteriaBuilder_stuff<self_t extends _ShopFavCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final IntegerField shopId = integer("shop");

    public final IntegerField userId = integer("\"user\"");

}
