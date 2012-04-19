package net.bodz.bas.c.type.order;

import java.lang.reflect.Method;
import java.util.Comparator;

import net.bodz.bas.util.order.AbstractNonNullComparator;
import net.bodz.bas.util.order.RefinedOrderComparator;
import net.bodz.bas.util.order.ReorderComparator;

public class TypeVectorComparator
        extends AbstractNonNullComparator<Class<?>[]> {

    private static final TypeComparator typecmp = TypeComparator.getInstance();

    @Override
    public int compareNonNull(Class<?>[] o1, Class<?>[] o2) {
        int share = Math.min(o1.length, o2.length);
        for (int i = 0; i < share; i++) {
            int cmp = typecmp.compare(o1[i], o2[i]);
            if (cmp != 0)
                return cmp;
        }
        return o1.length - o2.length;
    }

    static final TypeVectorComparator instance = new TypeVectorComparator();

    public static TypeVectorComparator getInstance() {
        return instance;
    }

    static final Comparator<Method> methodComparator;
    static {
        methodComparator = new RefinedOrderComparator<Method, String>(//
                new ReorderComparator<Method, Class<?>[]>(TypeVectorComparator.getInstance()) {
                    public Class<?>[] getOrder(Method method) {
                        return method.getParameterTypes();
                    }
                }) {
            @Override
            public String getOrder(Method object) {
                return object.getName();
            }
        };
    }

    public static Comparator<Method> getMethodComparator() {
        return methodComparator;
    }

}
