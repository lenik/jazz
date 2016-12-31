package net.bodz.bas.t.set;

import net.bodz.bas.c.object.IdentityHashSet;

public class Marks
        implements IMarks {

    IdentityHashSet<Object> idSet;

    public Marks() {
        idSet = new IdentityHashSet<>();
    }

    @Override
    public boolean contains(Object o) {
        return idSet.contains(o);
    }

    @Override
    public boolean add(Object o) {
        return idSet.add(o);
    }

    @Override
    public boolean remove(Object o) {
        return idSet.remove(o);
    }

    @Override
    public void clear() {
        idSet.clear();
    }

    @Override
    public String toString() {
        return idSet.toString();
    }

}
