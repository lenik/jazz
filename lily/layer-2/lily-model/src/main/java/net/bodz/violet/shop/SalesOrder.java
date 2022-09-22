package net.bodz.violet.shop;

import java.math.BigDecimal;

import javax.persistence.Table;

import org.joda.time.DateTime;

import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.meta.cache.Statistics;
import net.bodz.bas.meta.decl.Redundant;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.Person;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.entity.SizedList;
import net.bodz.lily.model.base.DefaultAccessMode;
import net.bodz.lily.security.IAccessMode;
import net.bodz.lily.t.base.CoMessage;
import net.bodz.violet.plan.Plan;
import net.bodz.violet.tran.TransportOrder;

/**
 * 订单
 *
 * op: 销售员/经办人
 *
 * owner: 制单
 */
@DefaultAccessMode(IAccessMode.M_COOP)
@IdType(Long.class)
@Table(name = "saleodr")
public class SalesOrder
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    private SalesCategory category;
    private SalesPhase phase;

    private SalesOrder previousOrder;
    private Plan plan;

    private Organization org;
    private Person person;

    private SizedList<SalesOrderItem> items = new SizedList<>();
    private BigDecimal totalQuantity = BigDecimal.ZERO;
    private BigDecimal totalAmount = BigDecimal.ZERO;

    // make-tasks
    // material-plans (locks)

    private SizedList<TransportOrder> deliveries;

    public SalesCategory getCategory() {
        return category;
    }

    public void setCategory(SalesCategory category) {
        this.category = category;
    }

    public SalesPhase getPhase() {
        return phase;
    }

    public void setPhase(SalesPhase phase) {
        this.phase = phase;
    }

    public SalesOrder getPreviousOrder() {
        return previousOrder;
    }

    public void setPreviousOrder(SalesOrder previousOrder) {
        this.previousOrder = previousOrder;
    }

    /**
     * 項目
     */
    @OfGroup(StdGroup.Process.class)
    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    /**
     * 企业
     */
    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }

    /**
     * 联系人
     */
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @DetailLevel(DetailLevel.HIDDEN)
    @Derived
    @Override
    public DateTime getBeginTime() {
        return super.getBeginTime();
    }

    @DetailLevel(DetailLevel.HIDDEN)
    @Derived
    @Override
    public DateTime getEndTime() {
        return super.getEndTime();
    }

    /**
     * 下单时间
     */
    @OfGroup(StdGroup.Schedule.class)
    public DateTime getOrderTime() {
        return super.getBeginTime();
    }

    public void setOrderTime(DateTime orderTime) {
        super.setBeginTime(orderTime);
    }

    /**
     * 交货期限
     */
    @OfGroup(StdGroup.Schedule.class)
    public DateTime getDeadline() {
        return super.getEndTime();
    }

    public void setDeadline(DateTime deadline) {
        super.setEndTime(deadline);
    }

    /**
     * 明细列表
     */
    @DetailLevel(DetailLevel.DETAIL)
    public SizedList<SalesOrderItem> getItems() {
        return items;
    }

    public void setItems(SizedList<SalesOrderItem> items) {
        this.items = items;
    }

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
    @OfGroup(StdGroup.Statistics.class)
    @Statistics
    public BigDecimal getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(BigDecimal totalQuantity) {
        if (totalQuantity == null)
            throw new NullPointerException("totalQuantity");
        this.totalQuantity = totalQuantity;
    }

    /**
     * 总金额
     */
    @OfGroup(StdGroup.Statistics.class)
    @Statistics
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        if (totalAmount == null)
            throw new NullPointerException("totalAmount");
        this.totalAmount = totalAmount;
    }

    /**
     * 送货跟踪
     */
    @DetailLevel(DetailLevel.DETAIL2)
    public SizedList<TransportOrder> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(SizedList<TransportOrder> deliveries) {
        this.deliveries = deliveries;
    }

}
