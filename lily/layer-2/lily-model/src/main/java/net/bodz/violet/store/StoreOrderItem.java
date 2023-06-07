package net.bodz.violet.store;

import java.math.BigDecimal;

import javax.persistence.Table;

import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.lily.model.base.DefaultAccessMode;
import net.bodz.lily.security.IAccessMode;
import net.bodz.lily.t.struct.IOrderItem;

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
    @Priority(202)
    @Derived
    @Override
    public synchronized BigDecimal getAmount() {
        if (amount == null)
            amount = price.multiply(quantity);
        return amount;
    }
}
