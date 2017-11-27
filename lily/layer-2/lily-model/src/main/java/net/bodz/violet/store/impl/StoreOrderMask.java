package net.bodz.violet.store.impl;

import net.bodz.bas.t.range.DoubleRange;
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

}
