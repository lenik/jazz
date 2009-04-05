package net.bodz.bas.types.ints;

import java.util.NoSuchElementException;

public abstract class PrefetchedIntIterator implements IntIterator {

    protected final int END;

    private int         prefetched;

    public PrefetchedIntIterator(int endValue) {
        this.END = endValue;
    }

    @Override
    public final boolean hasNext() {
        return prefetched != END;
    }

    @Override
    public final int next() {
        if (prefetched == END)
            throw new NoSuchElementException();
        int ret = prefetched;
        prefetched = fetch();
        return ret;
    }

    protected abstract int fetch();

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
