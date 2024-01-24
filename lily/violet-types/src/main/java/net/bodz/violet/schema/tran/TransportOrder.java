package net.bodz.violet.schema.tran;

import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.Table;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.meta.decl.Redundant;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.t.variant.IVarMapForm;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.meta.LilyGroups;
import net.bodz.lily.util.SizedList;
import net.bodz.violet.schema.shop.SalesOrderItem;

/**
 * 送货单
 */
@Table(schema = "violet", name = "tranodr")
public class TransportOrder
        extends _TransportOrder_stuff
        implements
            IVarMapForm {

    private static final long serialVersionUID = 1L;

    // Take-Out stock job
    // Account-Ticket

    private SizedList<TransportOrderItem> items = new SizedList<>();

    /**
     * 发货时间
     */
    @Priority(5)
    @OfGroup({ LilyGroups.Transportation.class })
    public ZonedDateTime getShipDate() {
        return super.getBeginTime();
    }

    public void setShipDate(ZonedDateTime shipDate) {
        super.setBeginTime(shipDate);
    }

    /**
     * 收货时间
     */
    @Priority(6)
    @OfGroup({ LilyGroups.Transportation.class })
    public ZonedDateTime getArrivedDate() {
        return super.getEndTime();
    }

    public void setArrivedDate(ZonedDateTime arrivedDate) {
        super.setEndTime(arrivedDate);
    }

    @Override
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

    @Override
    public void readObject(IVariantMap<String> map)
            throws LoaderException, ParseException {
        super.readObject(map);

        List<TransportOrderItem> items = getItems();
        for (String key : map.keySet()) {
            if (key.startsWith("qty-")) {
                String sidStr = key.substring(4);
                long sid;
                try {
                    sid = Long.parseLong(sidStr);
                } catch (NumberFormatException e) {
                    throw new ParseException(e);
                }
                double qty = map.getDouble(key, 0);
                String priceKey = "price-" + sid;
                double price = map.getDouble(priceKey, 0);

                TransportOrderItem item = new TransportOrderItem();
                item.setOrder(this);
                item.setSalesOrder(getSalesOrder());
                SalesOrderItem siRef = new SalesOrderItem();
                siRef.id(sid);
                item.setSalesOrderItem(siRef);
                item.setQuantity(qty);
                item.setPrice(price);
                items.add(item);
            }
        }
    }

}
