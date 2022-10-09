package net.bodz.lily.entity.type;

import java.beans.PropertyDescriptor;

import net.bodz.bas.t.order.AbstractNonNullComparator;

public class ColumnPropertyOrder
        extends AbstractNonNullComparator<ColumnProperty> {

    @Override
    public int compareNonNull(ColumnProperty o1, ColumnProperty o2) {
        int ordinal1 = o1.getOrdinal();
        int ordinal2 = o2.getOrdinal();
        int cmp = ordinal1 - ordinal2;
        if (cmp != 0)
            return cmp;

        int priority1 = o1.getPriority();
        int priority2 = o2.getPriority();
        cmp = priority1 - priority2;
        if (cmp != 0)
            return cmp;

        PropertyDescriptor pd1 = o1.getPropertyDescriptor();
        PropertyDescriptor pd2 = o2.getPropertyDescriptor();
        String name1 = pd1.getName();
        String name2 = pd2.getName();
        cmp = name1.compareTo(name2);
        if (cmp != 0)
            return cmp;

        // return pd1.equals(pd2) ? 0 : pd1.hashCode() - pd2.hashCode();
        return 0;
    }

    public static final ColumnPropertyOrder INSTANCE = new ColumnPropertyOrder();

}
