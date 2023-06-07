package net.bodz.violet.shop.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.shop.ShopItem;

/**
* @label ShopItem
*/
@ObjectType(ShopItem.class)
public class ShopItemIndex
        extends CoIndex<ShopItem, ShopItemMask> {

    public ShopItemIndex() {
    }

}
