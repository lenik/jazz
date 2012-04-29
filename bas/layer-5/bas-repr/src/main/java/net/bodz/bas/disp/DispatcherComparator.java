package net.bodz.bas.disp;

import net.bodz.bas.util.order.AbstractNonNullComparator;

public class DispatcherComparator
        extends AbstractNonNullComparator<IDispatcher> {

    @Override
    public int compareNonNull(IDispatcher a, IDispatcher b) {
        if (a == b)
            return 0;
        int cmp = a.getOrder() - b.getOrder();
        if (cmp == 0) {
            cmp = a.getName().compareTo(b.getName());
            if (cmp == 0)
                cmp = System.identityHashCode(a) - System.identityHashCode(b);
        }
        return cmp;
    }

    @Override
    public int getNullOrder() {
        return -1;
    }

    private static final DispatcherComparator instance = new DispatcherComparator();

    public static DispatcherComparator getInstance() {
        return instance;
    }

}
