package net.bodz.violet.tran;

import java.math.BigDecimal;

import javax.persistence.Table;

import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.meta.LilyGroups;
import net.bodz.lily.model.base.CoMomentInterval;
import net.bodz.lily.t.struct.IOrderItem;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.shop.SalesOrder;
import net.bodz.violet.shop.SalesOrderItem;

/**
 * 送货单项目
 */
@IdType(Long.class)
@Table(name = "tranodrl")
public class TransportOrderItem
        extends CoMomentInterval<Long>
        implements IOrderItem {

    private static final long serialVersionUID = 1L;

    public static final int N_ALT_LABEL = 30;
    public static final int N_ALT_SPEC = 80;

    TransportOrder order;
    SalesOrder salesOrder;
    SalesOrderItem salesOrderItem;
    Artifact artifact;

    BigDecimal quantity = BigDecimal.ZERO;
    BigDecimal price = BigDecimal.ZERO;
    BigDecimal amount;

    /**
     * 送货单
     */
    @OfGroup(StdGroup.Process.class)
    public TransportOrder getOrder() {
        return order;
    }

    public void setOrder(TransportOrder order) {
        this.order = order;
    }

    /**
     * 销售订单
     */
    @OfGroup(StdGroup.Process.class)
    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }

    /**
     * 源订单项
     */
    @OfGroup(StdGroup.Process.class)
    public SalesOrderItem getSalesOrderItem() {
        return salesOrderItem;
    }

    public void setSalesOrderItem(SalesOrderItem salesOrderItem) {
        this.salesOrderItem = salesOrderItem;
    }

    /**
     * 货物
     */
    @Derived
    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

    /**
     * 出货数量
     */
    @OfGroup(LilyGroups.Trade.class)
    @Priority(200)
    @Override
    public BigDecimal getQuantity() {
        return quantity;
    }

    @Override
    public synchronized void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
        this.amount = null;
    }

    @Override
    public void setQuantity(double quantity) {
        setQuantity(BigDecimal.valueOf(quantity));
    }

    /**
     * 出货价
     */
    @OfGroup(LilyGroups.Trade.class)
    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    @Priority(201)
    public synchronized void setPrice(BigDecimal price) {
        this.price = price;
        this.amount = null;
    }

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