package net.bodz.bas.potato.traits.util;

import net.bodz.bas.collection.comparator.AbstractNonNullComparator;
import net.bodz.bas.potato.traits.MethodKey;
import net.bodz.bas.type.comparator.TypeVectorComparator;

public class MethodKeyComparator
        extends AbstractNonNullComparator<MethodKey> {

    @Override
    public int compareNonNull(MethodKey o1, MethodKey o2) {
        int cmp = o1.getName().compareTo(o2.getName());
        if (cmp != 0)
            return cmp;

// XXX - Check this later.
        TypeVectorComparator vcmp = TypeVectorComparator.getInstance();
        Class<?>[] o1v = o1.getParameterTypes();
        Class<?>[] o2v = o2.getParameterTypes();
        return vcmp.compare(o1v, o2v);
    }

    private static final MethodKeyComparator instance = new MethodKeyComparator();

    public static MethodKeyComparator getInstance() {
        return instance;
    }

}