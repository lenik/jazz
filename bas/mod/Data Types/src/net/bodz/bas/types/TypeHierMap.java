package net.bodz.bas.types;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;

import net.bodz.bas.types.util.Comparators;

public class TypeHierMap<V> extends HierMap<Class<?>, V> {

    private static final long serialVersionUID = 2448961592894592311L;

    @Deprecated
    public TypeHierMap(Comparator<? super Class<?>> comparator) {
        super(comparator);
    }

    @Deprecated
    public TypeHierMap(Map<? extends Class<?>, ? extends V> m) {
        super(m);
    }

    @Deprecated
    public TypeHierMap(SortedMap<Class<?>, ? extends V> m) {
        super(m);
    }

    public TypeHierMap() {
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
