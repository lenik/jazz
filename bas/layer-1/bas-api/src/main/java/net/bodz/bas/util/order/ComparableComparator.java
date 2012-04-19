package net.bodz.bas.util.order;

import java.util.Comparator;


@SuppressWarnings({ "rawtypes", "unchecked" })
public class ComparableComparator
        extends AbstractNonNullComparator<Comparable> {

    @Override
    public int compareNonNull(Comparable o1, Comparable o2) {
        return o1.compareTo(o2);
    }

    private static final ComparableComparator instance = new ComparableComparator();

    public static <T extends Comparable> Comparator<T> getInstance() {
        return (Comparator<T>) instance;
    }

    public static Comparator<Object> getRawInstance() {
        return (Comparator<Object>) (Comparator<?>) instance;
    }

}
