package net.bodz.bas.c.reflect;

import net.bodz.bas.c.type.TypeVectorComparator;
import net.bodz.bas.util.Nullables;
import net.bodz.bas.util.order.AbstractNonNullComparator;

public class MethodSignatureComparator
        extends AbstractNonNullComparator<MethodSignature> {

    @Override
    public int compareNonNull(MethodSignature o1, MethodSignature o2) {
        int cmp = Nullables.compare(o1.getName(), o2.getName());
        if (cmp != 0)
            return cmp;

        // XXX - Check this later.
        TypeVectorComparator vcmp = TypeVectorComparator.getInstance();
        Class<?>[] o1v = o1.getParameterTypes();
        Class<?>[] o2v = o2.getParameterTypes();
        return vcmp.compare(o1v, o2v);
    }

    private static final MethodSignatureComparator instance = new MethodSignatureComparator();

    public static MethodSignatureComparator getInstance() {
        return instance;
    }

}