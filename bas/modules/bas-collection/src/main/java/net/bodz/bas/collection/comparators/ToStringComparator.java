package net.bodz.bas.collection.comparators;

public class ToStringComparator
        extends OrderComparator<Object, String> {

    @Override
    public String getOrder(Object object) {
        return object.toString();
    }

    static final ToStringComparator instance = new ToStringComparator();

    public static ToStringComparator getInstance() {
        return instance;
    }

}
