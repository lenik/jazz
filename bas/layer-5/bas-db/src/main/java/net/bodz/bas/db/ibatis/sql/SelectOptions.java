package net.bodz.bas.db.ibatis.sql;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVarMapForm;
import net.bodz.bas.t.variant.IVariantMap;

public class SelectOptions
        extends AbstractSelectionRange<SelectOptions>
        implements IVarMapForm {

    public static final String K_ORDER = "order";

    private Orders orders;

    public SelectOptions() {
        super();
    }

    public SelectOptions(long offset) {
        super(offset);
    }

    public SelectOptions(long offset, long limit) {
        super(offset, limit);
    }

    public Orders getOrders() {
        if (orders == null || orders.isEmpty())
            return null;
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public void setOrders(String orders) {
        this.orders = Orders.parse(orders);
    }

    public SelectOptions order(String column) {
        return order(column, true);
    }

    public SelectOptions order(String column, boolean ascending) {
        if (orders == null)
            orders = new Orders();
        Order order = new Order(column, ascending);
        orders.add(order);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());

        if (orders != null)
            sb.append(", order ").append(orders);
        else
            sb.append(", no-order");

        return sb.toString();
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws ParseException {
        super.readObject(map);

        String orderStr = map.getString(K_ORDER);
        if (orderStr != null)
            orders = Orders.parse(orderStr);
    }

    public static final SelectOptions TOP1 = new SelectOptions(0, 1);
    public static final SelectOptions TOP10 = new SelectOptions(0, 10);
    public static final SelectOptions TOP100 = new SelectOptions(0, 100);
    public static final SelectOptions TOP1000 = new SelectOptions(0, 1000);
    public static final SelectOptions ALL = new SelectOptions();

}
