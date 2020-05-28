package net.bodz.bas.db.ibatis.sql;

public class SelectOptions {

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

    public static final SelectOptions FIRST = new SelectOptions(new Pagination(1, 0));
    public static final SelectOptions ALL = new SelectOptions();

}
