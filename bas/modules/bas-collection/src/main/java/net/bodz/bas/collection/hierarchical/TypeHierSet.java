package net.bodz.bas.collection.hierarchical;

import java.util.Collection;

import net.bodz.bas.collection.comparator.TypeComparator;

public class TypeHierSet
        extends HierSet<Class<?>> {

    private static final long serialVersionUID = 1L;

    public TypeHierSet() {
        super(TypeComparator.getInstance());
    }

    public TypeHierSet(Collection<Class<?>> s) {
        this();
        addAll(s);
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
