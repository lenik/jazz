package net.bodz.violet.store.impl;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.model.base.CoNodeMask;

/**
 * @see net.bodz.violet.store.StorePhase
 */
public class StorePhaseMask
        extends CoNodeMask {

    @Override
    public void readObject(IVariantMap<String> map)
            throws ParseException {
        super.readObject(map);
    }

}
