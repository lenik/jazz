package net.bodz.violet.store.impl;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.template.CoCategoryIndex;
import net.bodz.violet.store.StoreCategory;

@ObjectType(StoreCategory.class)
public class StoreCategoryIndex
        extends CoCategoryIndex<StoreCategory, StoreCategoryMask> {

    public StoreCategoryIndex() {
    }

}
