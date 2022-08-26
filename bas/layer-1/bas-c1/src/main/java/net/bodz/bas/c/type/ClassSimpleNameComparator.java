package net.bodz.bas.c.type;

import net.bodz.bas.t.order.AbstractOrderComparator;

public class ClassSimpleNameComparator
        extends AbstractOrderComparator<Class<?>, String> {

    @Override
    public String getOrder(Class<?> object) {
        return object.getSimpleName();
    }

    public static final ClassSimpleNameComparator INSTANCE = new ClassSimpleNameComparator();

}
