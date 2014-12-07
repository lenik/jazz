package net.bodz.bas.repr.form;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.t.order.AbstractNonNullComparator;

public class FormFieldComparator
        extends AbstractNonNullComparator<IFormField> {

    @Override
    public int compareNonNull(IFormField o1, IFormField o2) {
        int cmp;

        int level1 = o1.getDetailLevel();
        int level2 = o2.getDetailLevel();
        cmp = level1 - level2;
        if (cmp != 0)
            return cmp;

        int priority1 = o1.getPriority();
        int priority2 = o2.getPriority();
        cmp = priority1 - priority2;
        if (cmp != 0)
            return cmp;

        String name1 = o1.getName();
        String name2 = o2.getName();
        // cmp = name1.compareTo(name2);
        cmp = Nullables.compare(name1, name2);
        if (cmp != 0)
            return cmp;

        int id1 = System.identityHashCode(o1);
        int id2 = System.identityHashCode(o2);
        cmp = id1 - id2;
        if (cmp != 0)
            return cmp;

        return o1.equals(o2) ? 0 : -1;
    }

    public static FormFieldComparator INSTANCE = new FormFieldComparator();

}
