package net.bodz.bas.types;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;

import net.bodz.bas.lang.Predicate2;
import net.bodz.bas.types.util.Comparators;

public class TypeHierMap<V> extends HierMap<Class<?>, V> {

    private static final long serialVersionUID = 2448961592894592311L;

    private static class Branchp implements Predicate2<Class<?>, Class<?>> {
        @Override
        public boolean eval(Class<?> a, Class<?> b) {
            if (a == b)
                return true;
            if (a == null || b == null)
                return false;
            if (a.isAssignableFrom(b))
                return true;
            if (b.isAssignableFrom(a))
                return true;
            return false;
        }
    }

    public static final Branchp BRANCHP = new Branchp();

    public TypeHierMap(Comparator<? super Class<?>> comparator) {
        super(BRANCHP, comparator);
    }

    public TypeHierMap(Map<? extends Class<?>, ? extends V> m) {
        super(BRANCHP, m);
    }

    public TypeHierMap(SortedMap<Class<?>, ? extends V> m) {
        super(BRANCHP, m);
    }

    public TypeHierMap() {
        super(BRANCHP, Comparators.TYPE_HIER);
    }

}
