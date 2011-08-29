package net.bodz.bas.util.order;

public class PriorityComparator
        extends AbstractNonNullComparator<IPriority> {

    @Override
    public int compareNonNull(IPriority o1, IPriority o2) {
        int cmp = o1.getPriority() - o2.getPriority();
        if (cmp != 0)
            return cmp;
        return compareIdentity(o1, o2);
    }

    public static final PriorityComparator INSTANCE = new PriorityComparator();

}
