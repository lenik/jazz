package net.bodz.bas.types.util;

import java.util.Iterator;

public abstract class LazyIterator<T> implements Iterator<T> {

    private Iterator<T> contentIter;

    @Override
    public boolean hasNext() {
        if (contentIter == null)
            contentIter = create();
        return contentIter.hasNext();
    }

    @Override
    public T next() {
        if (contentIter == null)
            contentIter = create();
        return contentIter.next();
    }

    @Override
    public void remove() {
        if (contentIter == null)
            contentIter = create();
        contentIter.remove();
    }

    protected abstract Iterator<T> create();

}
