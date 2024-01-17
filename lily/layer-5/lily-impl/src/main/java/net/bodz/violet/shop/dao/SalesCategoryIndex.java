package net.bodz.violet.shop.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.shop.SalesCategory;

/**
* @label SalesCategory
*/
@ObjectType(SalesCategory.class)
public class SalesCategoryIndex
        extends CoIndex<SalesCategory, SalesCategoryCriteriaBuilder> {

    public SalesCategoryIndex() {
    }

}
