package net.bodz.lily.entity.type;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.t.order.AbstractNonNullComparator;

public abstract class AbstractMethodOrder<T>
        extends AbstractNonNullComparator<T> {

    Map<String, Integer> methodNames = new LinkedHashMap<>();
    int nextOrder;
    int undefinedOrder = Integer.MAX_VALUE;

    public AbstractMethodOrder(Class<?> clazz) {
        scanMethods(clazz);
    }

    void scanMethods(Class<?> clazz) {
        Class<?> superclass = clazz.getSuperclass();
        if (superclass != Object.class && superclass != null)
            scanMethods(superclass);

        findMethods(clazz);
    }

    protected abstract void findMethods(Class<?> clazz);

    @Override
    public int compareNonNull(T o1, T o2) {
        Method method1 = getMethod(o1);
        Method method2 = getMethod(o2);

        String name1 = method1.getName();
        String name2 = method2.getName();

        Integer order1 = methodNames.get(name1);
        Integer order2 = methodNames.get(name2);
        int i1 = order1 == null ? undefinedOrder : order1;
        int i2 = order2 == null ? undefinedOrder : order2;

        int cmp = i1 - i2;
        if (cmp != 0)
            return cmp;

        cmp = name1.compareTo(name2);
        if (cmp != 0)
            return cmp;

        int n1 = method1.getParameterCount();
        int n2 = method2.getParameterCount();
        cmp = n1 - n2;
        if (cmp != 0)
            return cmp;

        Class<?>[] v1 = method1.getParameterTypes();
        Class<?>[] v2 = method2.getParameterTypes();
        for (int i = 0; i < n1; i++) {
            String type1 = v1[i].toString();
            String type2 = v2[i].toString();
            cmp = type1.compareTo(type2);
            if (cmp != 0)
                return cmp;
        }

        if (o1.equals(o2))
            return 0;
        else
            return -1;
    }

    protected abstract Method getMethod(T obj);

}