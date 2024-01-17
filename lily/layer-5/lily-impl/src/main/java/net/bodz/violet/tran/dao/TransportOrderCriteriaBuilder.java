package net.bodz.violet.tran.dao;

import net.bodz.lily.t.base.CoMessageCriteriaBuilder;

/**
 * @see net.bodz.violet.tran.TransportOrder
 */
public class TransportOrderCriteriaBuilder
        extends CoMessageCriteriaBuilder {

    public Integer salesOrderId;

    public Integer getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(Integer salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

}
