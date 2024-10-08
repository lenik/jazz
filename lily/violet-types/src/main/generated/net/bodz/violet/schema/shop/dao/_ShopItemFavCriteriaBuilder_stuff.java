package net.bodz.violet.schema.shop.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.shop.ShopItemFav;

@ForEntityType(ShopItemFav.class)
public class _ShopItemFavCriteriaBuilder_stuff<self_t extends _ShopItemFavCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final LongField shopItemId = _long("shopitem");

    public final IntegerField userId = integer("\"user\"");

}
