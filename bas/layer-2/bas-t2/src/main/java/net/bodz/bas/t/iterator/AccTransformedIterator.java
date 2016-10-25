package net.bodz.bas.t.iterator;

import java.util.Iterator;

import org.apache.commons.collections15.Transformer;

/**
 * Apache Commons-Collections transformer transformed iterator.
 */
public class AccTransformedIterator<S, T>
        implements Iterator<T> {

    private final Iterator<S> iterator;
    private final Transformer<S, T> transformer;

    public AccTransformedIterator(Iterator<S> iterator, Transformer<S, T> transformer) {
        if (iterator == null)
            throw new NullPointerException("iterator");
        if (transformer == null)
            throw new NullPointerException("transformer");
        this.iterator = iterator;
        this.transformer = transformer;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public T next() {
        S item = iterator.next();
        return transformer.transform(item);
    }

    @Override
    public void remove() {
        iterator.remove();
    }

}
