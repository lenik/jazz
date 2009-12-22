package net.bodz.bas.collection.hierarchical;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;

import net.bodz.bas.collection.comparators.TypeVectorComparator;

public class TypesHierMap<V>
        extends HierMap<Class<?>[], V> {

    private static final long serialVersionUID = 1453808200521926033L;

    @Deprecated
    public TypesHierMap(Comparator<? super Class<?>[]> comparator) {
        super(comparator);
    }

    @Deprecated
    public TypesHierMap(Map<? extends Class<?>[], ? extends V> m) {
        super(m);
    }

    @Deprecated
    public TypesHierMap(SortedMap<Class<?>[], ? extends V> m) {
        super(m);
    }

    public TypesHierMap() {
        super(TypeVectorComparator.getInstance());
    }

    @Override
    public boolean derives(Class<?>[] sup, Class<?>[] sub) {
        return TypesHierSet._derives(sup, sub);
    }

    @Override
    public Class<?>[] shrink(Class<?>[] e) {
        // copy...
        return TypesHierSet._shrink(e);
    }

}
