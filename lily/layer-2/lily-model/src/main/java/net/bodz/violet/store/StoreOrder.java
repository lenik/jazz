package net.bodz.violet.store;

import javax.persistence.Table;

import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.cache.Statistics;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.entity.SizedList;
import net.bodz.lily.model.contact.OrgUnit;
import net.bodz.lily.model.contact.Organization;
import net.bodz.lily.model.contact.Person;
import net.bodz.lily.model.mx.CoMessage;
import net.bodz.violet.plan.Plan;

/**
 * 库存作业
 */
@IdType(Integer.class)
// @SchemaPref(Schemas.STOCK)
@Table(name = "storeodr")
public class StoreOrder
        extends CoMessage<Integer> {

    private static final long serialVersionUID = 1L;

    private StoreCategory category;
    private StorePhase phase;

    private StoreOrder previous;
    private Plan plan;
    private Organization org;
    private OrgUnit orgUnit;
    private Person person;

    private SizedList<StoreOrderItem> items = new SizedList<>();
    private double quantity;
    private double total;

    public StoreOrder() {
    }

    @Override
    public void reinit() {
        super.reinit();
        setAccessMode(M_SHARED);

        StoreCategory TK_I = new StoreCategory();
        TK_I.setId(1202);
        TK_I.setLabel("采购入库");
        setCategory(TK_I);
    }

    /**
     * @label Category
     * @label.zh 分类
     */
    @OfGroup(StdGroup.Classification.class)
    public StoreCategory getCategory() {
        return category;
    }

    public void setCategory(StoreCategory category) {
        this.category = category;
    }

    @OfGroup(StdGroup.Status.class)
    public StorePhase getPhase() {
        return phase;
    }

    public void setPhase(StorePhase phase) {
        this.phase = phase;
    }

    /**
     * 前级
     */
    @OfGroup(StdGroup.Process.class)
    public StoreOrder getPrevious() {
        return previous;
    }

    public void setPrevious(StoreOrder previous) {
        this.previous = previous;
    }

    /**
     * 项目/机会
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
     * 部门
     */
    public OrgUnit getOrgUnit() {
        return orgUnit;
    }

    public void setOrgUnit(OrgUnit orgUnit) {
        this.orgUnit = orgUnit;
    }

    /**
     * 客户
     */
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * 明细
     */
    @DetailLevel(DetailLevel.EXTEND)
    public SizedList<StoreOrderItem> getItems() {
        return items;
    }

    public void setItems(SizedList<StoreOrderItem> items) {
        this.items = items;
    }

    /**
     * 总数量
     */
    @OfGroup(StdGroup.Statistics.class)
    @Statistics
    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     * 总金额
     */
    @OfGroup(StdGroup.Statistics.class)
    @Statistics
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public synchronized void update() {
        quantity = 0;
        total = 0;
        for (StoreOrderItem item : items) {
            quantity += item.getQuantity();
            total += item.getTotal();
        }

    }

}
