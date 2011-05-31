package net.bodz.bas.type.comparator;

import net.bodz.bas.util.order.AbstractNonNullComparator;

public class TypeComparator
        extends AbstractNonNullComparator<Class<?>> {

    @Override
    public int compareNonNull(Class<?> t1, Class<?> t2) {
        if (t1.isInterface()) {
            if (t2.isInterface())
                return InterfaceComparator.getInstance().compare(t1, t2);
            else
                return -1; // interface is smaller!
        } else {
            if (t2.isInterface())
                return 1;
            else
                return ClassComparator.getInstance().compare(t1, t2);
        }
    }

    static final TypeComparator instance = new TypeComparator();

    public static TypeComparator getInstance() {
        return instance;
    }

}
