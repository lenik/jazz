package net.bodz.bas.collection.iterator;

public abstract class AbstractIteratorMX<T, X extends Throwable>
        implements IteratorMX<T, X> {

    private boolean ended;

    @Override
    public boolean isOverlapped() {
        return false;
    }

    @Override
    public T deoverlap(T o) {
        return o;
    }

    protected T end() {
        this.ended = true;
        return null;
    }

    @Override
    public boolean isEnded() {
        return ended;
    }

    @Override
    public int skip(int n)
            throws X {
        int actualSkipped = 0;
        for (int i = 0; i < n; i++) {
            if (_next() == null && isEnded())
                break;
            actualSkipped++;
        }
        return actualSkipped;
    }

    @Override
    public IteratorMX<T, X> mark() {
        throw new UnsupportedOperationException();
    }

    @Override
    public IteratorX<T, X> toIterator() {
        return new IteratorM2X<T, X>(this);
    }

}
