package net.bodz.bas.collection.iterator;

public abstract class AbstractImmediateIterator<T, X extends Throwable>
        implements ImmediateIterator<T, X> {

    private boolean ended;

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
    public ImmediateIterator<T, X> mark() {
        throw new UnsupportedOperationException();
    }

}
