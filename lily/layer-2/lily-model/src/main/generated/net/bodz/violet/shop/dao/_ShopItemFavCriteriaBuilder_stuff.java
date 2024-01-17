package net.bodz.violet.shop.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _ShopItemFavCriteriaBuilder_stuff<self_t extends _ShopItemFavCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final LongField shopItemId = _long("shopitem");

    public final IntegerField userId = integer("\"user\"");

}
