package net.bodz.bas.t._int;

import net.bodz.bas.err.NotImplementedException;

public abstract class _IntSortedSet
        extends _IntSet
        implements IntSortedSet {

    @Override
    public IntComparator comparator() {
        return IntComparator.ASCEND;
    }

    @Override
    public IntSortedSet headSet(int toElement) {
        throw new NotImplementedException();
    }

    @Override
    public IntSortedSet subSet(int fromElement, int toElement) {
        throw new NotImplementedException();
    }

    @Override
    public IntSortedSet tailSet(int fromElement) {
        throw new NotImplementedException();
    }

    @Override
    public int first() {
        throw new NotImplementedException();
    }

    @Override
    public int last() {
        throw new NotImplementedException();
    }

}
