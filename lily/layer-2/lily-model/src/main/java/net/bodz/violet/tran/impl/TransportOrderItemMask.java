package net.bodz.violet.tran.impl;

import net.bodz.lily.model.base.CoObjectMask;

/**
 * @see net.bodz.violet.tran.TransportOrderItem
 */
public class TransportOrderItemMask
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

}
