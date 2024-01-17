package net.bodz.violet.shop.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.shop.ShopFav;

/**
* @label ShopFav
*/
@ObjectType(ShopFav.class)
public class ShopFavIndex
        extends CoIndex<ShopFav, ShopFavCriteriaBuilder> {

    public ShopFavIndex() {
    }

}
