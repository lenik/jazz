package net.bodz.bas.c.type;

import net.bodz.bas.util.order.AbstractOrderComparator;

public class ClassNameComparator
        extends AbstractOrderComparator<Class<?>, String> {

    @Override
    public String getOrder(Class<?> clazz) {
        return clazz.getName();
    }

    static final ClassNameComparator instance = new ClassNameComparator();

    public static ClassNameComparator getInstance() {
        return instance;
    }

}
