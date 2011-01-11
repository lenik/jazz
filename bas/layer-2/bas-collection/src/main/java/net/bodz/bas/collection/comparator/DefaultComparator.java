package net.bodz.bas.collection.comparator;

import net.bodz.bas.exceptions.IllegalUsageException;

public class DefaultComparator
        implements NonNullComparator<Object> {

    @Override
    public int compare(Object o1, Object o2) {
        if (o1 instanceof Comparable<?>) {
            @SuppressWarnings("unchecked")
            Comparable<Object> c1 = (Comparable<Object>) o1;
            return c1.compareTo(o2);
        }
        throw new IllegalUsageException("Default comparator used to compare non-comparable objects");
    }

    private static final DefaultComparator instance = new DefaultComparator();

    public static DefaultComparator getInstance() {
        return instance;
    }

}
