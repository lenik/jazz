package net.bodz.bas.collection.iterator;

public abstract class OverlappedImmediateIteratorX<T, X extends Exception>
        extends AbstractImmediateIteratorX<T, X> {

    @Override
    public boolean isOverlapped() {
        return true;
    }

    @Override
    public abstract T deoverlap(T o);

}
