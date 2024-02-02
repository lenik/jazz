package net.bodz.bas.esm;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.t.order.AbstractNonNullComparator;

public class EsmNameComparator
        extends AbstractNonNullComparator<EsmName> {

    @Override
    public int compareNonNull(EsmName o1, EsmName o2) {
        int cmp = EsmSourceComparator.INSTANCE.compare(o1.source, o2.source);
        if (cmp != 0)
            return cmp;

        cmp = o1.priority - o2.priority;
        if (cmp != 0)
            return cmp;

        cmp = Nullables.compare(o1.name, o2.name);
        if (cmp != 0)
            return cmp;

        cmp = Nullables.compare(o1.alias, o2.alias);
        if (cmp != 0)
            return cmp;

        return 0;
    }

    public static final EsmNameComparator INSTANCE = new EsmNameComparator();

}
