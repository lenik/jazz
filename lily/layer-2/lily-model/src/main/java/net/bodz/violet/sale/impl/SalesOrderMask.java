package net.bodz.violet.sale.impl;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.model.mx.CoMessageMask;

/**
 * @see net.bodz.violet.sale.SalesOrder
 */
public class SalesOrderMask
        extends CoMessageMask {

    @Override
    public void readObject(IVariantMap<String> map)
            throws ParseException {
        super.readObject(map);
    }

}
