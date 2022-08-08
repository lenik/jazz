package net.bodz.bas.db.ibatis.sql;

import java.util.Map;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.t.variant.IVarMapForm;
import net.bodz.bas.t.variant.IVariantMap;

public class SelectOptions
        implements
            IVarMapForm {

    private Pagination page;
    private Orders orders;

    public SelectOptions() {
    }

    public SelectOptions(Pagination page) {
        this.page = page;
    }

    public SelectOptions(Pagination page, Orders orders) {
        this.page = page;
        this.orders = orders;
    }

    public Pagination getPage() {
        return page;
    }

    public void setPage(Pagination page) {
        this.page = page;
    }

    public SelectOptions page(int limit, int offset) {
        if (page == null)
            page = new Pagination();
        page.setLimitOffset(limit, offset);
        return this;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public SelectOptions order(String column, boolean ascending) {
        if (orders == null)
            orders = new Orders();
        Order order = new Order(column, ascending);
        orders.add(order);
        return this;
    }

    @Override
    public void readObject(IVariantMap<String> map) {
        Long _pageIndex = map.getLong("page.index");
        Long _pageNumber = map.getLong("page.number");
        Integer _pageSize = map.getInt("page.size");

        if (_pageIndex != null || _pageNumber != null || _pageSize != null) {
            page = new Pagination();

            int pageSize = _pageSize == null ? 100 : _pageSize.intValue();
            page.setLimit(pageSize);

            if (_pageIndex != null)
                page.setPageIndex(_pageIndex.longValue());

            if (_pageNumber != null)
                page.setPageNumber(_pageNumber.longValue());
        } else {
            page = null;
        }
    }

    @Override
    public void writeObject(Map<String, Object> map) {
        throw new NotImplementedException();
    }

    public static final SelectOptions FIRST = new SelectOptions(new Pagination(1, 0));
    public static final SelectOptions ALL = new SelectOptions();

}
