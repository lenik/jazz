package net.bodz.violet.store.dao;

import net.bodz.lily.template.CoCategoryMask;

/**
 * @see net.bodz.violet.store.StoreCategory
 */
public class StoreCategoryMask
        extends CoCategoryMask {

    public static StoreCategoryMask below(int maxDepth) {
        StoreCategoryMask mask = new StoreCategoryMask();
        mask.maxDepth = 1;
        return mask;
    }

}
