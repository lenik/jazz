package net.bodz.violet.shop.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.template.CoCategoryIndex;
import net.bodz.violet.shop.ShopItemCategory;

/**
* @label ShopItemCategory
*/
@ObjectType(ShopItemCategory.class)
public class ShopItemCategoryIndex
        extends CoCategoryIndex<ShopItemCategory, ShopItemCategoryMask> {

    public ShopItemCategoryIndex() {
    }

}
