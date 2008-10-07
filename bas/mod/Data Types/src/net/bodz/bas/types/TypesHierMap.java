package net.bodz.bas.types;

import static net.bodz.bas.types.TypesHierSet.minherits;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;

import net.bodz.bas.types.util.Comparators;

public class TypesHierMap<V> extends HierMap<Class<?>[], V> {

    private static final long serialVersionUID = 1453808200521926033L;

    @Deprecated
    public TypesHierMap(Comparator<? super Class<?>[]> comparator) {
        super(minherits, comparator);
    }

    @Deprecated
    public TypesHierMap(Map<? extends Class<?>[], ? extends V> m) {
        super(minherits, m);
    }

    @Deprecated
    public TypesHierMap(SortedMap<Class<?>[], ? extends V> m) {
        super(minherits, m);
    }

    public TypesHierMap() {
        super(minherits, Comparators.TYPES_HIER);
    }

}
