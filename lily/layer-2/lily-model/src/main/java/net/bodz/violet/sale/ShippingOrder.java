package net.bodz.violet.sale;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Table;

import org.joda.time.DateTime;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.cache.Statistics;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.bas.t.variant.IVarMapSerializable;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.meta.LilyGroups;
import net.bodz.lily.model.base.security.User;
import net.bodz.lily.model.contact.Contact;
import net.bodz.lily.model.contact.Organization;
import net.bodz.lily.model.contact.Person;
import net.bodz.lily.model.mx.CoMessage;

/**
 * 送货单
 */
@IdType(Integer.class)
@Table(name = "shipodr")
public class ShippingOrder
        extends CoMessage<Integer>
        implements IVarMapSerializable {

    private static final long serialVersionUID = 1L;

    private ShippingOrder previous;
    private SalesOrder salesOrder;

    private Organization org;
    private Person person;

    private Contact shipDest;
    private Organization shipper;
    private String shipmentId;
    private double shippingCost;

    // Take-Out stock job
    // Account-Ticket

    private List<ShippingOrderItem> items = new ArrayList<>();
    private int itemCount = SIZE_UNKNOWN;
    private double quantity;
    private double total;

    /**
     * 前级
     */
    @OfGroup(StdGroup.Process.class)
    public ShippingOrder getPrevious() {
        return previous;
    }

    public void setPrevious(ShippingOrder previous) {
        this.previous = previous;
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
        return super.getBeginDate();
    }

    public void setShipDate(DateTime shipDate) {
        super.setBeginDate(shipDate);
    }

    /**
     * 收货时间
     */
    @Priority(6)
    @OfGroup({ LilyGroups.Transportation.class })
    public DateTime getArrivedDate() {
        return super.getEndDate();
    }

    public void setArrivedDate(DateTime arrivedDate) {
        super.setEndDate(arrivedDate);
    }

    /**
     * 明细条数
     */
    @DetailLevel(DetailLevel.EXTEND)
    public int getItemCount() {
        return SizeFn.getSize(items, itemCount);
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
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

    /**
     * 明细列表
     */
    @DetailLevel(DetailLevel.EXTEND)
    public List<ShippingOrderItem> getItems() {
        return items;
    }

    public void setItems(List<ShippingOrderItem> items) {
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
            throws ParseException {
        List<ShippingOrderItem> items = getItems();

        for (String key : map.keySet()) {
            if (key.startsWith("qty-")) {
                String sidStr = key.substring(4);
                long sid = Long.parseLong(sidStr);
                double qty = map.getDouble(key);
                String priceKey = "price-" + sid;
                double price = map.getDouble(priceKey);

                ShippingOrderItem item = new ShippingOrderItem();
                item.setDelivery(this);
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

    @Override
    public void writeObject(IVariantMap<String> map) {
    }

}
