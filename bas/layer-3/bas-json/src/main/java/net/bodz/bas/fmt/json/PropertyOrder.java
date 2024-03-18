package net.bodz.bas.fmt.json;

import net.bodz.bas.bean.api.IPropertyDescriptor;
import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.t.order.AbstractNonNullComparator;

public class PropertyOrder
        extends AbstractNonNullComparator<IPropertyDescriptor> {

    @Override
    public int compareNonNull(IPropertyDescriptor o1, IPropertyDescriptor o2) {
        boolean d1 = o1.isAnnotationPresent(Derived.class);
        boolean d2 = o2.isAnnotationPresent(Derived.class);
        if (d1 != d2)
            return d1 ? 1 : -1;

        int pos1 = o1.getPosition();
        int pos2 = o2.getPosition();
        int cmp = pos1 - pos2;
        if (cmp != 0)
            return cmp;

        return -1;
    }

    public static final PropertyOrder INSTANCE = new PropertyOrder();

}