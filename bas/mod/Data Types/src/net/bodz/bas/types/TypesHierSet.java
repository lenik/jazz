package net.bodz.bas.types;

import static net.bodz.bas.types.TypeHierSet.inherits;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;

import net.bodz.bas.lang.Predicate2s;
import net.bodz.bas.types.util.Comparators;

public class TypesHierSet extends HierSet<Class<?>[]> {

    private static final long serialVersionUID = -8867244757204813499L;

    static class MInherits implements Predicate2s<Class<?>[]> {
        @Override
        public boolean eval(Class<?>[] av, Class<?>[] bv) {
            if (av == bv)
                return true;
            if (av == null || bv == null)
                return false;
            if (av.length != bv.length)
                return false;
            for (int i = 0; i < av.length; i++) {
                Class<?> a = av[i];
                Class<?> b = bv[i];
                if (!inherits.eval(a, b))
                    return false;
            }
            return true;
        }
    }

    static final MInherits minherits = new MInherits();

    @Deprecated
    public TypesHierSet(Collection<? extends Class<?>[]> c) {
        super(minherits, c);
    }

    @Deprecated
    public TypesHierSet(Comparator<? super Class<?>[]> comparator) {
        super(minherits, comparator);
    }

    @Deprecated
    public TypesHierSet(SortedSet<Class<?>[]> s) {
        super(minherits, s);
    }

    public TypesHierSet() {
        super(minherits, Comparators.TYPES_HIER);
    }

}
