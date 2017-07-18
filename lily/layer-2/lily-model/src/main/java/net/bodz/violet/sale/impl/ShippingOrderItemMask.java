package net.bodz.violet.sale.impl;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.model.base.CoObjectMask;

/**
 * @see net.bodz.violet.sale.ShippingOrderItem
 */
public class ShippingOrderItemMask
        extends CoObjectMask {

    public Integer shippingOrderId;
    public Integer salesOrderId;

    public Integer getShippingOrderId() {
        return shippingOrderId;
    }

    public void setShippingOrderId(Integer shippingOrderId) {
        this.shippingOrderId = shippingOrderId;
    }

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
        shippingOrderId = map.getInt("shippingOrderId", shippingOrderId);
        salesOrderId = map.getInt("salesOrderId", salesOrderId);
    }

}
