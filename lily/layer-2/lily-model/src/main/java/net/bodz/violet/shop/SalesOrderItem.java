package net.bodz.violet.shop;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import javax.persistence.Table;

import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;

/**
 * 订单项
 */
@Table(schema = "violet", name = "saleodrl")
public class SalesOrderItem
        extends _SalesOrderItem_stuff {

    private static final long serialVersionUID = 1L;

    @DetailLevel(DetailLevel.HIDDEN)
    @Override
    public ZonedDateTime getBeginTime() {
        return super.getBeginTime();
    }

    @DetailLevel(DetailLevel.HIDDEN)
    @Override
    public ZonedDateTime getEndTime() {
        return super.getEndTime();
    }

    /**
     * 订单时间
     */
    @OfGroup(StdGroup.Schedule.class)
    public ZonedDateTime getOrderTime() {
        return super.getBeginTime();
    }

    public void setOrderTime(ZonedDateTime orderTime) {
        super.setBeginTime(orderTime);
    }

    /**
     * 交货时间
     */
    @OfGroup(StdGroup.Schedule.class)
    public ZonedDateTime getDeadline() {
        return super.getEndTime();
    }

    public void setDeadline(ZonedDateTime deadline) {
        super.setEndTime(deadline);
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
