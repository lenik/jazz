package net.bodz.violet.schema.store;

import java.math.BigDecimal;

import javax.persistence.Table;

import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.lily.concrete.util.IOrderItem;
import net.bodz.lily.meta.DefaultAccessMode;
import net.bodz.lily.security.IAccessMode;

/**
 * 库存操作项目
 */
@DefaultAccessMode(IAccessMode.M_SHARED)
@Table(schema = "violet", name = "storeodrl")
public class StoreOrderItem
        extends _StoreOrderItem_stuff
        implements
            IOrderItem {

    private static final long serialVersionUID = 1L;

    BigDecimal amount;

    @Override
    public void setQuantity(double quantity) {
        setQuantity(BigDecimal.valueOf(quantity));
    }

    @Override
    public void setPrice(double price) {
        setPrice(BigDecimal.valueOf(price));
    }

    /**
     * 总额
     */
    @Derived
    @Override
    @Priority(202)
    public synchronized BigDecimal getAmount() {
        if (amount == null)
            amount = price.multiply(quantity);
        return amount;
    }
}
