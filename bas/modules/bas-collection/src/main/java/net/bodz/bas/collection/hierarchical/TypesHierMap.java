package net.bodz.bas.collection.hierarchical;

import net.bodz.bas.collection.comparator.TypeVectorComparator;

public class TypesHierMap<V>
        extends HierMap<Class<?>[], V> {

    private static final long serialVersionUID = 1453808200521926033L;

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
