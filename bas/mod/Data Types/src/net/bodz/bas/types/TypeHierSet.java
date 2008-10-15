package net.bodz.bas.types;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;

import net.bodz.bas.types.util.Comparators;

public class TypeHierSet extends HierSet<Class<?>> {

    private static final long serialVersionUID = 419433681459061242L;

    @Deprecated
    public TypeHierSet(Collection<? extends Class<?>> c) {
        super(c);
    }

    @Deprecated
    public TypeHierSet(Comparator<? super Class<?>> comparator) {
        super(comparator);
    }

    @Deprecated
    public TypeHierSet(SortedSet<Class<?>> s) {
        super(s);
    }

    public TypeHierSet() {
        super(Comparators.TYPE_HIER);
    }

    @Override
    public boolean derives(Class<?> sup, Class<?> sub) {
        return sup.isAssignableFrom(sub);
    }

    @Override
    public Class<?> shrink(Class<?> e) {
        return e.getSuperclass();
    }

}
