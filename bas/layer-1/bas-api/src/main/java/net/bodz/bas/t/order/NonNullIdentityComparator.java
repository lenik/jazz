package net.bodz.bas.t.order;

import java.util.Comparator;

public class NonNullIdentityComparator
        implements Comparator<Object> {

    @Override
    public int compare(Object o1, Object o2) {
        int id1 = System.identityHashCode(o1);
        int id2 = System.identityHashCode(o2);
        int cmp = id1 - id2;
        if (cmp != 0)
            return cmp;
        if (o1 == o2)
            return 0;
        else
            return -1;
    }

    public static final NonNullIdentityComparator INSTANCE = new NonNullIdentityComparator();

}
