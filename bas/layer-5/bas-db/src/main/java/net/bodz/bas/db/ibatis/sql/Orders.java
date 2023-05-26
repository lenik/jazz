package net.bodz.bas.db.ibatis.sql;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.text.StringTokenizer;

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

    public static Orders parse(String s) {
        if (s == null)
            return null;
        Orders list = new Orders();
        StringTokenizer tokens = new StringTokenizer(s, ",");
        while (tokens.hasNext()) {
            String token = tokens.next();
            token = token.trim();
            if (token.isEmpty()) // to allow trailing ",".
                continue;
            Order order = Order.parse(token);
            list.add(order);
        }
        return list;
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
