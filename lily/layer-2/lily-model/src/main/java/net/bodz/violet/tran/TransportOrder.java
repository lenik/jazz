package net.bodz.violet.tran;

import java.util.List;

import javax.persistence.Table;

import org.joda.time.DateTime;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.cache.Statistics;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.meta.decl.Redundant;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.bas.t.variant.IVarMapSerializable;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.contact.Contact;
import net.bodz.lily.contact.OrgUnit;
import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.Person;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.entity.SizedList;
import net.bodz.lily.meta.LilyGroups;
import net.bodz.lily.model.mx.CoMessage;
import net.bodz.lily.security.User;
import net.bodz.violet.shop.SalesOrder;
import net.bodz.violet.shop.SalesOrderItem;
import net.bodz.violet.store.StoreOrder;

/**
 * 送货单
 */
@IdType(Integer.class)
@Table(name = "tranodr")
public class TransportOrder
        extends CoMessage<Integer>
        implements IVarMapSerializable {

    private static final long serialVersionUID = 1L;

    private TransportCategory category;
    private TransportPhase phase;

    private TransportOrder previousOrder;
    private SalesOrder salesOrder;
    private StoreOrder storeOrder;

    private Organization org;
    private OrgUnit orgUnit;
    private Person person;

    private Contact shipDest;
    private Organization shipper;
    private String shipmentId;
    private double shippingCost;

    // Take-Out stock job
    // Account-Ticket

    private SizedList<TransportOrderItem> items = new SizedList<>();
    private double totalQuantity;
    private double totalAmount;

    public TransportCategory getCategory() {
        return category;
    }

    public void setCategory(TransportCategory category) {
        this.category = category;
    }

    public TransportPhase getPhase() {
        return phase;
    }

    public void setPhase(TransportPhase phase) {
        this.phase = phase;
    }

    /**
     * 前级
     */
    @OfGroup(StdGroup.Process.class)
    public TransportOrder getPreviousOrder() {
        return previousOrder;
    }

    public void setPreviousOrder(TransportOrder previousOrder) {
        this.previousOrder = previousOrder;
    }

    /**
     * 订单
     *
     * @placeholder 选择一个源始订单…
     */
    @OfGroup(StdGroup.Process.class)
    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }

    /**
     * 库存作业
     *
     * @placeholder 选择一个源始库存作业…
     */
    @OfGroup(StdGroup.Process.class)
    public StoreOrder getStoreOrder() {
        return storeOrder;
    }

    public void setStoreOrder(StoreOrder storeOrder) {
        this.storeOrder = storeOrder;
    }

    /**
     * 公司
     *
     * @placeholder 选择目标公司…
     */
    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }

    public OrgUnit getOrgUnit() {
        return orgUnit;
    }

    public void setOrgUnit(OrgUnit orgUnit) {
        this.orgUnit = orgUnit;
    }

    /**
     * 联系人
     *
     * @placeholder 选择联系人…
     */
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * 目的地
     *
     * @placeholder 选择目的地…
     */
    @Priority(1)
    @OfGroup(LilyGroups.Transportation.class)
    public Contact getShipDest() {
        return shipDest;
    }

    public void setShipDest(Contact shipDest) {
        this.shipDest = shipDest;
    }

    /**
     * 承运人
     *
     * @placeholder 选择承运人…
     */
    @Priority(2)
    @OfGroup(LilyGroups.Transportation.class)
    public Organization getShipper() {
        return shipper;
    }

    public void setShipper(Organization shipper) {
        this.shipper = shipper;
    }

    /**
     * 运单号
     *
     * @placeholder 输入运单号…
     */
    @Priority(3)
    @OfGroup(LilyGroups.Transportation.class)
    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    /**
     * 运费
     */
    @Priority(4)
    @OfGroup({ LilyGroups.Transportation.class })
    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    /**
     * 发货时间
     */
    @Priority(5)
    @OfGroup({ LilyGroups.Transportation.class })
    public DateTime getShipDate() {
        return super.getBeginTime();
    }

    public void setShipDate(DateTime shipDate) {
        super.setBeginTime(shipDate);
    }

    /**
     * 收货时间
     */
    @Priority(6)
    @OfGroup({ LilyGroups.Transportation.class })
    public DateTime getArrivedDate() {
        return super.getEndTime();
    }

    public void setArrivedDate(DateTime arrivedDate) {
        super.setEndTime(arrivedDate);
    }

    /**
     * 总数量
     */
    @OfGroup(StdGroup.Statistics.class)
    @Statistics
    public double getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(double totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    /**
     * 总金额
     */
    @OfGroup(StdGroup.Statistics.class)
    @Statistics
    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Redundant
    public int getLength() {
        return items.size();
    }

    /**
     * 明细列表
     */
    @DetailLevel(DetailLevel.DETAIL)
    public SizedList<TransportOrderItem> getItems() {
        return items;
    }

    public void setItems(SizedList<TransportOrderItem> items) {
        this.items = items;
    }

    /**
     * 经办人
     */
    @Override
    // @OfGroup(OaGroups.UserInteraction.class)
    public User getOp() {
        return super.getOp();
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws LoaderException {
        super.readObject(map);

        List<TransportOrderItem> items = getItems();
        for (String key : map.keySet()) {
            if (key.startsWith("qty-")) {
                String sidStr = key.substring(4);
                long sid = Long.parseLong(sidStr);
                double qty = map.getDouble(key, 0);
                String priceKey = "price-" + sid;
                double price = map.getDouble(priceKey, 0);

                TransportOrderItem item = new TransportOrderItem();
                item.setOrder(this);
                item.setSalesOrder(getSalesOrder());
                SalesOrderItem siRef = new SalesOrderItem();
                siRef.setId(sid);
                item.setSalesOrderItem(siRef);
                item.setQuantity(qty);
                item.setPrice(price);
                items.add(item);
            }
        }
    }

}
