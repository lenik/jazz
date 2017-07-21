package net.bodz.violet.plan.impl;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.model.base.CoNodeMask;

/**
 * @see net.bodz.violet.plan.PlanTag
 */
public class PlanTagMask
        extends CoNodeMask {

    @Override
    public void readObject(IVariantMap<String> _map)
            throws ParseException {
        super.readObject(_map);

        // QVariantMap<String> map = QVariantMap.from(_map);
    }

}
