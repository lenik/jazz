package net.bodz.bas.types;

import java.util.Collection;

import net.bodz.bas.types.util.Comparators;

public class TypeHierSet extends HierSet<Class<?>> {

    private static final long serialVersionUID = 419433681459061242L;

    public TypeHierSet() {
        super(Comparators.TYPE_HIER);
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
