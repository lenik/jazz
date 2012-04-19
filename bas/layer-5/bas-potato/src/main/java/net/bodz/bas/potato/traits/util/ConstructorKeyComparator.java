package net.bodz.bas.potato.traits.util;

import net.bodz.bas.c.type.order.TypeVectorComparator;
import net.bodz.bas.potato.traits.ConstructorKey;
import net.bodz.bas.util.order.AbstractNonNullComparator;

public class ConstructorKeyComparator
        extends AbstractNonNullComparator<ConstructorKey> {

    @Override
    public int compareNonNull(ConstructorKey o1, ConstructorKey o2) {
// XXX - Check this later.
        TypeVectorComparator vcmp = TypeVectorComparator.getInstance();
        Class<?>[] o1v = o1.getParameterTypes();
        Class<?>[] o2v = o2.getParameterTypes();
        return vcmp.compare(o1v, o2v);
    }

    private static final ConstructorKeyComparator instance = new ConstructorKeyComparator();

    public static ConstructorKeyComparator getInstance() {
        return instance;
    }

}