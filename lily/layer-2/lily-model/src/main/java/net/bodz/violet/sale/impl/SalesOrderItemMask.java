package net.bodz.violet.sale.impl;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.model.base.CoObjectMask;

/**
 * @see net.bodz.violet.sale.SalesOrderItem
 */
public class SalesOrderItemMask
        extends CoObjectMask {

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
        salesOrderId = map.getInt("salesOrderId", salesOrderId);
    }

}
