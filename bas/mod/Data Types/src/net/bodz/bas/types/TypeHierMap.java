package net.bodz.bas.types;

import static net.bodz.bas.types.TypeHierSet.inherits;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;

import net.bodz.bas.types.util.Comparators;

public class TypeHierMap<V> extends HierMap<Class<?>, V> {

    private static final long serialVersionUID = 2448961592894592311L;

    public TypeHierMap(Comparator<? super Class<?>> comparator) {
        super(inherits, comparator);
    }

    public TypeHierMap(Map<? extends Class<?>, ? extends V> m) {
        super(inherits, m);
    }

    public TypeHierMap(SortedMap<Class<?>, ? extends V> m) {
        super(inherits, m);
    }

    public TypeHierMap() {
        super(inherits, Comparators.TYPE_HIER);
    }

}
