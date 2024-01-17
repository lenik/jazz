package net.bodz.violet.store.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.template.CoCategoryIndex;
import net.bodz.violet.store.StoreCategory;

/**
* @label StoreCategory
*/
@ObjectType(StoreCategory.class)
public class StoreCategoryIndex
        extends CoCategoryIndex<StoreCategory, StoreCategoryCriteriaBuilder> {

    public StoreCategoryIndex() {
    }

}
