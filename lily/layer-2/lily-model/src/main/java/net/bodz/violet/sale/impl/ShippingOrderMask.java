package net.bodz.violet.sale.impl;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.model.mx.CoMessageMask;

/**
 * @see net.bodz.violet.sale.ShippingOrder
 */
public class ShippingOrderMask
        extends CoMessageMask {

    public Integer salesOrderId;

    public Integer getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(Integer salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws ParseException {
        super.readObject(map);
        salesOrderId = map.getInt("saleodr", salesOrderId);
    }

}
