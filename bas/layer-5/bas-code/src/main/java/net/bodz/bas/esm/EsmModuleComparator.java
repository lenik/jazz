package net.bodz.bas.esm;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.t.order.AbstractNonNullComparator;

public class EsmModuleComparator
        extends AbstractNonNullComparator<EsmModule> {

    @Override
    public int compareNonNull(EsmModule o1, EsmModule o2) {
        int cmp = o1.priority - o2.priority;
        if (cmp != 0)
            return cmp;

        cmp = Nullables.compare(o1.name, o2.name);
        if (cmp != 0)
            return cmp;

        return 0;
    }

    public static final EsmModuleComparator INSTANCE = new EsmModuleComparator();

}
