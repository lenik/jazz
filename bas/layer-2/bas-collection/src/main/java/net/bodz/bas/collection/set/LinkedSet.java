package net.bodz.bas.collection.set;

import java.util.LinkedList;
import java.util.Set;

public class LinkedSet<E>
        extends OrderPreservedSet<E> {

    private static final long serialVersionUID = 8594169168395837363L;

    public LinkedSet() {
        super(new LinkedList<E>());
    }

    public LinkedSet(Set<E> set) {
        super(new LinkedList<E>(set));
    }

}
