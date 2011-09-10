package net.bodz.bas.collection.iterator;

public abstract class OverlappedIteratorMX<T, X extends Exception>
        extends AbstractIteratorMX<T, X> {

    @Override
    public boolean isOverlapped() {
        return true;
    }

    @Override
    public abstract T deoverlap(T o);

}
