package net.bodz.bas.db.ibatis.sql;

import java.util.ArrayList;
import java.util.Collection;

public class Orders
        extends ArrayList<Order> {

    private static final long serialVersionUID = 1L;

    public Orders() {
        super();
    }

    public Orders(Collection<? extends Order> c) {
        super(c);
    }

    public Orders(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Order o : this) {
            if (sb.length() != 0)
                sb.append(", ");
            sb.append(o);
        }
        return sb.toString();
    }

}
