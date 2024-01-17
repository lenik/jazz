package net.bodz.violet.store.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.template.CoCategoryIndex;
import net.bodz.violet.store.RegionCategory;

/**
 * Region Category
 */
@ObjectType(RegionCategory.class)
public class RegionCategoryIndex
        extends CoCategoryIndex<RegionCategory, RegionCategoryCriteriaBuilder> {

    public RegionCategoryIndex() {
    }

}
