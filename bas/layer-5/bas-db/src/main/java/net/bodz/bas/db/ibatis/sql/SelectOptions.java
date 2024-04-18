package net.bodz.bas.db.ibatis.sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.t.variant.IVarMapForm;
import net.bodz.bas.t.variant.IVariantMap;

public class SelectOptions
        implements
            IVarMapForm {

    public static final String K_OFFSET = "page.offset";
    public static final String K_LIMIT = "page.limit";
    public static final String K_PAGE_INDEX = "page.index";
    public static final String K_PAGE_NUMBER = "page.number";
    public static final String K_PAGE_SIZE = "page.size";
    public static final String K_ORDERS = "order";

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
        if (orders == null || orders.size() == 0)
            return null;
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public void setOrders(String orders) {
        this.orders = Orders.parse(orders);
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
        List<String> list = new ArrayList<>();
        if (orders != null)
            list.add("order " + orders);
        else
            list.add("NoOrder");

        if (page != null)
            list.add("page " + page);
        else
            list.add("ALL");

        return list.toString();
    }

    @Override
    public void readObject(IVariantMap<String> map) {
        Long offset = map.getLong(K_OFFSET);
        Long limit = map.getLong(K_LIMIT);
        if (offset != null || limit != null) {
            if (page == null)
                page = new Pagination();
            if (offset != null)
                page.offset = offset;
            if (limit != null)
                page.limit = limit;
        }

        Long _pageIndex = map.getLong(K_PAGE_INDEX);
        Long _pageNumber = map.getLong(K_PAGE_NUMBER);
        Integer _pageSize = map.getInt(K_PAGE_SIZE);

        if (_pageIndex != null || _pageNumber != null || _pageSize != null) {
            if (page == null)
                page = new Pagination();

            int pageSize = _pageSize == null ? 100 : _pageSize.intValue();
            page.setLimit(pageSize);

            if (_pageIndex != null)
                page.setPageIndex(_pageIndex.longValue());

            if (_pageNumber != null)
                page.setPageNumber(_pageNumber.longValue());
        }

        String orderBy = map.getString(K_ORDERS);
        if (orderBy != null)
            orders = Orders.parse(orderBy);
    }

    @Override
    public void writeObject(Map<String, Object> map) {
        throw new NotImplementedException();
    }

    public static final SelectOptions TOP1 = new SelectOptions(new Pagination(1, 0));
    public static final SelectOptions TOP10 = new SelectOptions(new Pagination(10, 0));
    public static final SelectOptions TOP100 = new SelectOptions(new Pagination(100, 0));
    public static final SelectOptions TOP1000 = new SelectOptions(new Pagination(1000, 0));
    public static final SelectOptions ALL = new SelectOptions();

}
