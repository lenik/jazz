package net.bodz.bas.types;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;

import net.bodz.bas.lang.Predicate2s;

public class TypeHierSet extends HierSet<Class<?>> {

    private static final long serialVersionUID = 419433681459061242L;

    static class Inherits implements Predicate2s<Class<?>> {
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

    static final Inherits inherits = new Inherits();

    public TypeHierSet(Collection<? extends Class<?>> c) {
        super(inherits, c);
    }

    public TypeHierSet(Comparator<? super Class<?>> comparator) {
        super(inherits, comparator);
    }

    public TypeHierSet(SortedSet<Class<?>> s) {
        super(inherits, s);
    }

    public TypeHierSet() {
        super(inherits);
    }

}
