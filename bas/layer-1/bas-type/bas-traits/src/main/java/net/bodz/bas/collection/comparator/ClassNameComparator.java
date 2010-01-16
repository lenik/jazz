package net.bodz.bas.collection.comparator;

import net.bodz.bas.collection.comparator.OrderComparator;

public class ClassNameComparator
        extends OrderComparator<Class<?>, String> {

    @Override
    public String getOrder(Class<?> clazz) {
        return clazz.getName();
    }

    static final ClassNameComparator instance = new ClassNameComparator();

    public static ClassNameComparator getInstance() {
        return instance;
    }

}
