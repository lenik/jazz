package net.bodz.lily.entity.type;

import net.bodz.bas.t.order.AbstractNonNullComparator;
import net.bodz.mda.xjdoc.util.MethodId;

public class MethodIdComparator
        extends AbstractNonNullComparator<MethodId> {

    @Override
    public int compareNonNull(MethodId o1, MethodId o2) {
        String name1 = o1.getMethodName();
        String name2 = o2.getMethodName();
        int cmp = name1.compareTo(name2);
        if (cmp != 0)
            return cmp;

        int n1 = o1.getParameterCount();
        int n2 = o2.getParameterCount();
        cmp = n1 - n2;
        if (cmp != 0)
            return cmp;

        for (int i = 0; i < n1; i++) {
            String type1 = o1.getParameterType(i);
            String type2 = o2.getParameterType(i);
            cmp = type1.compareTo(type2);
            if (cmp != 0)
                return cmp;

            int dc1 = o1.getDimensionCount(i);
            int dc2 = o2.getDimensionCount(i);
            cmp = dc1 - dc2;
            if (cmp != 0)
                return cmp;
        }

        if (o1.equals(o2))
            return 0;
        else
            return -1;
    }

    public static final MethodIdComparator INSTANCE = new MethodIdComparator();

}
