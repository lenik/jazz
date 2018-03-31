package net.bodz.violet.shop.impl;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoCategoryIndex;
import net.bodz.violet.shop.ShopItemCategory;

@ObjectType(ShopItemCategory.class)
public class ShopItemCategoryIndex
        extends CoCategoryIndex<ShopItemCategory, ShopItemCategoryMask> {

    public static final String SCHEMA = "shopitemcat";

    public ShopItemCategoryIndex() {
        super(SCHEMA);
    }

}
