package net.bodz.violet.shop.impl;

import net.bodz.lily.model.base.CoObjectMask;

/**
 * @see net.bodz.violet.shop.SalesOrderItem
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

}
