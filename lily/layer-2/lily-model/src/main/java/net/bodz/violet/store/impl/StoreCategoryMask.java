package net.bodz.violet.store.impl;

import net.bodz.lily.model.base.CoCategoryMask;

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
