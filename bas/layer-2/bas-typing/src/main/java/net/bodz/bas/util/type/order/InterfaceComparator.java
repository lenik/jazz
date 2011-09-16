package net.bodz.bas.util.type.order;

import java.util.Arrays;

import net.bodz.bas.util.order.AbstractNonNullComparator;
import net.bodz.bas.util.type.order.InterfaceComparatorTest;

/**
 * @test {@link InterfaceComparatorTest}
 */
public class InterfaceComparator
        extends AbstractNonNullComparator<Class<?>> {

    Class<?> getRootOfFirstBranch(Class<?> iface) {
        assert iface != null;
        Class<?>[] parents = iface.getInterfaces();
        if (parents.length == 0)
            return iface;
        return getRootOfFirstBranch(parents[0]);
    }

    @Override
    public int compareNonNull(Class<?> o1, Class<?> o2) {
        assert o1.isInterface();
        assert o2.isInterface();

        if (o1.equals(o2))
            return 0;

        while (true) {
            if (o1.isAssignableFrom(o2))
                return -1;
            else if (o2.isAssignableFrom(o1))
                return 1;

            Class<?>[] o1v = o1.getInterfaces();
            if (o1v.length == 0)
                return ClassNameComparator.getInstance().compare(o1, getRootOfFirstBranch(o2));

            Class<?>[] o2v = o2.getInterfaces();
            if (o2v.length == 0)
                return ClassNameComparator.getInstance().compare(getRootOfFirstBranch(o1), o2);

            if (o1v.length == 1 && o2v.length == 1) {
                Class<?> p1 = o1v[0];
                Class<?> p2 = o2v[0];
                if (p1.equals(p2))
                    return ClassNameComparator.getInstance().compare(o1, o2);
                o1 = p1;
                o2 = p2;
                continue;
            }

            Arrays.sort(o1v, this);
            Arrays.sort(o2v, this);
            int share = Math.min(o1v.length, o2v.length);
            for (int i = 0; i < share; i++) {
                int cmp = compare(o1v[i], o2v[i]);
                if (cmp != 0)
                    return cmp;
            }
            return o1v.length - o2v.length;
        }
    }

    static final InterfaceComparator instance = new InterfaceComparator();

    public static InterfaceComparator getInstance() {
        return instance;
    }

}
