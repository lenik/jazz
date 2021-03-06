package net.bodz.bas.t.order;

public class ToStringComparator
        extends AbstractOrderComparator<Object, String> {

    @Override
    public String getOrder(Object object) {
        return object.toString();
    }

    public static final ToStringComparator INSTANCE = new ToStringComparator();

}
