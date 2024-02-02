package net.bodz.bas.esm;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.t.order.AbstractNonNullComparator;

public class EsmSourceComparator
        extends AbstractNonNullComparator<EsmSource> {

    @Override
    public int compareNonNull(EsmSource o1, EsmSource o2) {
        int cmp = EsmModuleComparator.INSTANCE.compare(o1.module, o2.module);
        if (cmp != 0)
            return cmp;

        cmp = o1.priority - o2.priority;
        if (cmp != 0)
            return cmp;

        cmp = Nullables.compare(o1.path, o2.path);
        if (cmp != 0)
            return cmp;

        return 0;
    }

    public static final EsmSourceComparator INSTANCE = new EsmSourceComparator();

}
