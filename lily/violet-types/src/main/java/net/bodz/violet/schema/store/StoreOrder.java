package net.bodz.violet.schema.store;

import java.math.BigDecimal;

import javax.persistence.Table;

import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.decl.Redundant;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.bas.repr.form.meta.StdGroup.Statistics;
import net.bodz.lily.meta.DefaultAccessMode;
import net.bodz.lily.security.IAccessMode;
import net.bodz.lily.util.SizedList;


/**
 * 库存作业
 */
@DefaultAccessMode(IAccessMode.M_SHARED)
// @SchemaPref(Schemas.STOCK)
@Table(schema = StoreOrder.SCHEMA_NAME, name = StoreOrder.TABLE_NAME)
public class StoreOrder
        extends _StoreOrder_stuff {

    private static final long serialVersionUID = 1L;


    private SizedList<StoreOrderItem> items = new SizedList<>();
    private BigDecimal totalQuantity = BigDecimal.ZERO;
    private BigDecimal totalAmount = BigDecimal.ZERO;

    public StoreOrder() {
        StoreCategory TK_I = new StoreCategory();
        TK_I.id(1202);
        TK_I.setLabel("采购入库");
        setCategory(TK_I);
    }

    /**
     * 明细
     */
    @DetailLevel(DetailLevel.DETAIL)
    public SizedList<StoreOrderItem> getItems() {
        return items;
    }

    public void setItems(SizedList<StoreOrderItem> items) {
        this.items = items;
    }

    @Override
    @Redundant
    public int getLength() {
        if (items == null)
            return 0;
        else
            return items.size();
    }

    /**
     * 总数量
     */
    @Override
    @OfGroup(StdGroup.Statistics.class)
    @Statistics
    public BigDecimal getTotalQuantity() {
        return totalQuantity;
    }

    @Override
    public void setTotalQuantity(BigDecimal totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    /**
     * 总金额
     */
    @Override
    @OfGroup(StdGroup.Statistics.class)
    @Statistics
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    @Override
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public synchronized void update() {
        totalQuantity = BigDecimal.ZERO;
        totalAmount = BigDecimal.ZERO;
        for (StoreOrderItem item : items) {
            totalQuantity = totalQuantity.add(item.getQuantity());
            totalAmount = totalAmount.add(item.getAmount());
        }
    }

}
