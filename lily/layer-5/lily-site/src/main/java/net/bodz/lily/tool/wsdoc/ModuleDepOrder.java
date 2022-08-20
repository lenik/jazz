package net.bodz.lily.tool.wsdoc;

import net.bodz.bas.t.order.AbstractNonNullComparator;

public class ModuleDepOrder
        extends AbstractNonNullComparator<ModuleInfo> {

    @Override
    public int compareNonNull(ModuleInfo o1, ModuleInfo o2) {
        if (o1.isDependency(o2))
            return 1;

        String name1 = o1.getModule().getName();
        String name2 = o2.getModule().getName();
        int cmp = name1.compareTo(name2);
        if (cmp != 0)
            return cmp;

        return -1;
    }

    public static final ModuleDepOrder INSTANCE = new ModuleDepOrder();

}
