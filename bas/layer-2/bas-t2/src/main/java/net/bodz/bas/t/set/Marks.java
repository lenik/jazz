package net.bodz.bas.t.set;

import java.util.Set;

import net.bodz.bas.c.object.IdentityHashSet;

public class Marks
        implements
            IMarkset {

    Set<Object> idSet;

    public Marks() {
        this(new IdentityHashSet<Object>());
    }

    public Marks(Set<Object> idSet) {
        this.idSet = idSet;
    }

    @Override
    public boolean containsMark(Object o) {
        return idSet.contains(o);
    }

    @Override
    public boolean addMark(Object o) {
        return idSet.add(o);
    }

    @Override
    public boolean removeMark(Object o) {
        return idSet.remove(o);
    }

    @Override
    public void clearMarks() {
        idSet.clear();
    }

    @Override
    public String toString() {
        return idSet.toString();
    }

}
