package net.bodz.bas.util.iter;

import java.util.Iterator;

import org.apache.commons.collections15.Transformer;

public class TransformedIterator<S, T>
        implements Iterator<T> {

    private final Iterator<S> iterator;
    private final Transformer<S, T> transformer;

    public TransformedIterator(Iterator<S> iterator, Transformer<S, T> transformer) {
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
