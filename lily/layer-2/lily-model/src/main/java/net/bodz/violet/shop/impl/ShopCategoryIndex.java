package net.bodz.violet.shop.impl;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoCategoryIndex;
import net.bodz.violet.shop.ShopCategory;

@ObjectType(ShopCategory.class)
public class ShopCategoryIndex
        extends CoCategoryIndex<ShopCategory, ShopCategoryMask> {

    public static final String SCHEMA = "shopcat";

    public ShopCategoryIndex() {
        super(SCHEMA);
    }

}
