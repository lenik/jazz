package net.bodz.bas.collection.iterator;

public abstract class AbstractImmediateIteratorX<T, X extends Exception>
        implements ImmediateIteratorX<T, X> {

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
            if (next() == null && isEnded())
                break;
            actualSkipped++;
        }
        return actualSkipped;
    }

    @Override
    public ImmediateIteratorX<T, X> mark() {
        throw new UnsupportedOperationException();
    }

}
