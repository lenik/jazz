package net.bodz.bas.util.lang;

import net.bodz.bas.util.order.OrderComparator;

public class ToStringComparator
        extends OrderComparator<Object, String> {

    @Override
    public String getOrder(Object object) {
        return object.toString();
    }

    public static final ToStringComparator INSTANCE = new ToStringComparator();

}
