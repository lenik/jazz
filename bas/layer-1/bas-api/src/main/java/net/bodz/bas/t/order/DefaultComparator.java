package net.bodz.bas.t.order;

import java.util.Comparator;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class DefaultComparator
        extends AbstractNonNullComparator<Comparable> {

    @Override
    public int compareNonNull(Comparable o1, Comparable o2) {
        return o1.compareTo(o2);
    }

    private static final DefaultComparator _instance = new DefaultComparator();

    public static <T extends Comparable> Comparator<T> getInstance() {
        return (Comparator<T>) _instance;
    }

    public static Comparator<Object> INSTANCE = (Comparator<Object>) (Object) _instance;

}
