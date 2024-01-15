package net.bodz.lily.criterion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Composite
        extends Criterion
        implements
            Iterable<ICriterion> {

    protected List<ICriterion> list = new ArrayList<>();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator<ICriterion> iterator() {
        return list.iterator();
    }

    public boolean add(ICriterion e) {
        return list.add(e);
    }

    public boolean remove(Object o) {
        return list.remove(o);
    }

    public void clear() {
        list.clear();
    }

}
