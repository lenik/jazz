package net.bodz.violet.schema.tran;

import java.math.BigDecimal;

import javax.persistence.Table;

import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.lily.concrete.util.IOrderItem;
import net.bodz.lily.meta.LilyGroups;
import net.bodz.violet.schema.shop.SalesOrder;
import net.bodz.violet.schema.shop.SalesOrderItem;

/**
 * 送货单项目
 */
@Table(schema = "violet", name = "tranodrl")
public class TransportOrderItem
        extends _TransportOrderItem_stuff
        implements
            IOrderItem {

    private static final long serialVersionUID = 1L;

    SalesOrder salesOrder;
    SalesOrderItem salesOrderItem;

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }

    public SalesOrderItem getSalesOrderItem() {
        return salesOrderItem;
    }

    public void setSalesOrderItem(SalesOrderItem salesOrderItem) {
        this.salesOrderItem = salesOrderItem;
    }

    @Override
    public void setQuantity(double quantity) {
        setQuantity(BigDecimal.valueOf(quantity));
    }

    @Override
    public void setPrice(double price) {
        setPrice(BigDecimal.valueOf(price));
    }

    /**
     * 出货金额
     */
    @Derived
    @OfGroup(LilyGroups.Trade.class)
    @Override
    @Priority(202)
    public synchronized BigDecimal getAmount() {
        if (amount == null)
            amount = price.multiply(quantity);
        return amount;
    }

}
