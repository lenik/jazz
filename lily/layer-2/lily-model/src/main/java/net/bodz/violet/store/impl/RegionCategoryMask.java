package net.bodz.violet.store.impl;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.model.base.CoNodeMask;
import net.bodz.violet.store.RegionCategory;

/**
 * @see RegionCategory
 */
public class RegionCategoryMask
        extends CoNodeMask {

    @Override
    public void readObject(IVariantMap<String> _map)
            throws ParseException {
        super.readObject(_map);

        // QVariantMap<String> map = QVariantMap.from(_map);
    }

}
