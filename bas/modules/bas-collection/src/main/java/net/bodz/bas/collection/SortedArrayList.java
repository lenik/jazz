package net.bodz.bas.collection;

import java.util.ArrayList;
import java.util.Comparator;

public class SortedArrayList<E> extends SortedList<E> {

    private static final long serialVersionUID = 6924338303261196419L;

    public SortedArrayList(int initialCapacity, Comparator<? super E> comparator) {
        super(new ArrayList<E>(initialCapacity), comparator);
    }

    public SortedArrayList(Comparator<? super E> comparator) {
        super(new ArrayList<E>(), comparator);
    }

    public SortedArrayList() {
        super(new ArrayList<E>());
    }

}
