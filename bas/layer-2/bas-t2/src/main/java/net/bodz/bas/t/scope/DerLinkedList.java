package net.bodz.bas.t.scope;

import java.util.LinkedList;
import java.util.List;

public class DerLinkedList<E>
        extends DerList<E> {

    public DerLinkedList(List<E> orig) {
        super(orig);
    }

    @Override
    protected List<E> createAllocList() {
        return new LinkedList<E>();
    }

}
