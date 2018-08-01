package net.bodz.bas.db.ibatis.sql;

public class SelectOptions {

    private Pagination page;
    private Orders orders;

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

}
