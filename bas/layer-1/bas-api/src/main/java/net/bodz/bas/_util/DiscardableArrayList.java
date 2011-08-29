package net.bodz.bas._util;

import java.util.ArrayList;
import java.util.Collection;

public class DiscardableArrayList<E>
        extends ArrayList<E>
        implements IDiscardableList<E> {

    private static final long serialVersionUID = 1L;

    public DiscardableArrayList() {
        super();
    }

    public DiscardableArrayList(Collection<? extends E> c) {
        super(c);
    }

    public DiscardableArrayList(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public boolean addAtPreferredIndex(int index, E element) {
        add(index, element);
        return true;
    }

}
