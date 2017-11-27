package net.bodz.violet.tran;

import javax.persistence.Table;

import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.meta.LilyGroups;
import net.bodz.lily.model.base.CoMomentInterval;
import net.bodz.violet.shop.SalesOrder;
import net.bodz.violet.shop.SalesOrderItem;
import net.bodz.violet.store.Artifact;

/**
 * 送货单项目
 */
@IdType(Long.class)
@Table(name = "tranodrl")
public class TransportOrderItem
        extends CoMomentInterval<Long> {

    private static final long serialVersionUID = 1L;

    public static final int N_ALT_LABEL = 30;
    public static final int N_ALT_SPEC = 80;

    TransportOrder delivery;
    SalesOrder salesOrder;
    SalesOrderItem salesOrderItem;
    Artifact artifact;

    double quantity;
    double price;

    /**
     * 送货单
     */
    @OfGroup(StdGroup.Process.class)
    public TransportOrder getDelivery() {
        return delivery;
    }

    public void setDelivery(TransportOrder delivery) {
        this.delivery = delivery;
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
    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     * 出货价
     */
    @OfGroup(LilyGroups.Trade.class)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * 出货金额
     */
    @OfGroup(LilyGroups.Trade.class)
    @Derived
    public double getTotal() {
        return price * quantity;
    }

}