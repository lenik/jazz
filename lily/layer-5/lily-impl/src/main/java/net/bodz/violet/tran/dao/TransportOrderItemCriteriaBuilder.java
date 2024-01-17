package net.bodz.violet.tran.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

/**
 * @see net.bodz.violet.tran.TransportOrderItem
 */
public class TransportOrderItemCriteriaBuilder
        extends CoObjectCriteriaBuilder {

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
