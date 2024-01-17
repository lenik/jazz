package net.bodz.violet.shop.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.shop.Shop;

/**
* @label Shop
*/
@ObjectType(Shop.class)
public class ShopIndex
        extends CoIndex<Shop, ShopCriteriaBuilder> {

    public ShopIndex() {
    }

}
