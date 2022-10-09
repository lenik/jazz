package net.bodz.lily.entity.type;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.t.order.AbstractNonNullComparator;

public abstract class AbstractFieldOrder
        extends AbstractNonNullComparator<Field> {

    Map<String, Integer> fieldNames = new LinkedHashMap<>();
    int nextOrder;
    int undefinedOrder = Integer.MAX_VALUE;

    public AbstractFieldOrder(Class<?> clazz) {
        scanFields(clazz);
    }

    void scanFields(Class<?> clazz) {
        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null)
            scanFields(clazz);

        findFields(clazz);
    }

    protected abstract void findFields(Class<?> clazz);

    @Override
    public int compareNonNull(Field o1, Field o2) {
        String name1 = o1.getName();
        String name2 = o2.getName();

        Integer order1 = fieldNames.get(name1);
        Integer order2 = fieldNames.get(name2);
        int i1 = order1 == null ? undefinedOrder : order1;
        int i2 = order2 == null ? undefinedOrder : order2;

        int cmp = i1 - i2;
        if (cmp != 0)
            return cmp;

        cmp = name1.compareTo(name2);
        if (cmp != 0)
            return cmp;

        return 0;
    }

}