package net.bodz.violet.shop.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.template.CoCategoryIndex;
import net.bodz.violet.shop.ShopCategory;

/**
* @label ShopCategory
*/
@ObjectType(ShopCategory.class)
public class ShopCategoryIndex
        extends CoCategoryIndex<ShopCategory, ShopCategoryCriteriaBuilder> {

    public ShopCategoryIndex() {
    }

}
