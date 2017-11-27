package net.bodz.violet.store.impl;

import net.bodz.lily.model.base.CoNodeMask;

/**
 * @see net.bodz.violet.store.StoreCategory
 */
public class StoreCategoryMask
        extends CoNodeMask {

    public static StoreCategoryMask below(int maxDepth) {
        StoreCategoryMask mask = new StoreCategoryMask();
        mask.maxDepth = 1;
        return mask;
    }

}
