package net.bodz.bas.repr.rest;

import net.bodz.bas.util.order.AbstractNonNullComparator;

public class RESTfulViewComparator
        extends AbstractNonNullComparator<IRESTfulView> {

    @Override
    public int compareNonNull(IRESTfulView o1, IRESTfulView o2) {
        int priority1 = o1.getPriority();
        int priority2 = o2.getPriority();

        int cmp = priority1 - priority2;
        if (cmp != 0)
            return cmp;

        cmp = System.identityHashCode(o1) - System.identityHashCode(o2);
        return cmp;
    }

    public static final RESTfulViewComparator INSTANCE = new RESTfulViewComparator();

}
