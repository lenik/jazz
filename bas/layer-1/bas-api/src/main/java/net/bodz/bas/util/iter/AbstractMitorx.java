package net.bodz.bas.util.iter;

public abstract class AbstractMitorx<T, X extends Throwable>
        implements Mitorx<T, X> {

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
    public Mitorx<T, X> mark() {
        throw new UnsupportedOperationException();
    }

}
