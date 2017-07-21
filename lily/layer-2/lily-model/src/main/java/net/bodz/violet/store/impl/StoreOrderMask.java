package net.bodz.violet.store.impl;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.range.DoubleRange;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.bas.t.variant.QVariantMap;
import net.bodz.lily.model.mx.CoMessageMask;

/**
 * @see net.bodz.violet.store.StoreOrder
 */
public class StoreOrderMask
        extends CoMessageMask {

    public Long planId;
    public Integer orgId;
    public Integer orgUnitId;
    public Integer personId;
    public DoubleRange totalRange;

    @Override
    public void readObject(IVariantMap<String> _map)
            throws ParseException {
        super.readObject(_map);
        QVariantMap<String> map = QVariantMap.from(_map);
        planId = map.getLong("plan", planId);
        orgId = map.getInt("org", orgId);
        orgUnitId = map.getInt("ou", orgUnitId);
        personId = map.getInt("person", personId);
        totalRange = map.getDoubleRange("totals", totalRange);
    }

}
