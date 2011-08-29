package net.bodz.bas.collection.set;

import java.util.ArrayList;
import java.util.Set;

public class ArraySet<E>
        extends OrderPreservedSet<E> {

    private static final long serialVersionUID = 8594169168395837363L;

    public ArraySet() {
        super(new ArrayList<E>());
    }

    public ArraySet(int initialCapacity) {
        super(new ArrayList<E>(initialCapacity));
    }

    public ArraySet(Set<E> set) {
        super(new ArrayList<E>(set));
    }

}
