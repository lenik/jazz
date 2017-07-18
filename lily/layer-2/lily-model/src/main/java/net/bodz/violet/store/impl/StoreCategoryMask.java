package net.bodz.violet.store.impl;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.model.base.CoNodeMask;

/**
 * @see net.bodz.violet.store.StoreCategory
 */
public class StoreCategoryMask
        extends CoNodeMask {

    @Override
    public void readObject(IVariantMap<String> map)
            throws ParseException {
        super.readObject(map);
    }

    public static StoreCategoryMask below(int maxDepth) {
        StoreCategoryMask mask = new StoreCategoryMask();
        mask.maxDepth = 1;
        return mask;
    }

}
