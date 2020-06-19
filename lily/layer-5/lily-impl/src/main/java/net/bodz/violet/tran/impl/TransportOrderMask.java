package net.bodz.violet.tran.impl;

import net.bodz.lily.t.base.CoMessageMask;

/**
 * @see net.bodz.violet.tran.TransportOrder
 */
public class TransportOrderMask
        extends CoMessageMask {

    public Integer salesOrderId;

    public Integer getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(Integer salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

}
