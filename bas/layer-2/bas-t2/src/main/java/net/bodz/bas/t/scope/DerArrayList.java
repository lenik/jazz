package net.bodz.bas.t.scope;

import java.util.ArrayList;
import java.util.List;

public class DerArrayList<E>
        extends DerList<E> {

    public DerArrayList(List<E> orig) {
        super(orig);
    }

    @Override
    protected List<E> createAllocList() {
        return new ArrayList<E>();
    }

}
