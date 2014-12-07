package net.bodz.bas.t.order;

import java.util.Comparator;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class DefaultDescendingComparator
        extends AbstractNonNullComparator<Comparable> {

    @Override
    public int compareNonNull(Comparable o1, Comparable o2) {
        return o2.compareTo(o1);
    }

    private static final DefaultDescendingComparator _instance = new DefaultDescendingComparator();

    public static <T extends Comparable> Comparator<T> getInstance() {
        return (Comparator<T>) _instance;
    }

    public static Comparator<Object> INSTANCE = (Comparator<Object>) (Object) _instance;

}
