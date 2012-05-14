package net.bodz.bas.disp.view;

import net.bodz.bas.util.order.AbstractNonNullComparator;

public class ViewRendererComparator
        extends AbstractNonNullComparator<IViewRenderer> {

    @Override
    public int compareNonNull(IViewRenderer o1, IViewRenderer o2) {
        int priority1 = o1.getPriority();
        int priority2 = o2.getPriority();

        int cmp = priority1 - priority2;
        if (cmp != 0)
            return cmp;

        cmp = System.identityHashCode(o1) - System.identityHashCode(o2);
        return cmp;
    }

    public static final ViewRendererComparator INSTANCE = new ViewRendererComparator();

}
