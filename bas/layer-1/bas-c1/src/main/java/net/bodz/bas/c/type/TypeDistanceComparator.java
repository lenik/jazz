package net.bodz.bas.c.type;

import java.util.Comparator;

import net.bodz.bas.t.order.AbstractNonNullComparator;

public class TypeDistanceComparator
        extends AbstractNonNullComparator<Class<?>> {

    private Class<?> base;
    private TypeSpace typeSpace = TypeSpace.getDefault();
    private Comparator<Class<?>> subcmp = TypeComparator.getInstance();

    public TypeDistanceComparator(Class<?> base) {
        if (base == null)
            throw new NullPointerException("base");
        this.base = base;
    }

    @Override
    public int compareNonNull(Class<?> o1, Class<?> o2) {
        int d1 = typeSpace.dist(base, o1);
        int d2 = typeSpace.dist(base, o2);
        int cmp = d1 - d2;
        if (cmp != 0)
            return cmp;
        return subcmp.compare(o1, o2);
    }

}
