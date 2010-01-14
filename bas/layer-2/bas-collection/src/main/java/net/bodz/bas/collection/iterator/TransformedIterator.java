package net.bodz.bas.collection.iterator;

import java.util.Iterator;

import net.bodz.bas.collection.transform.ElTransformer;

public class TransformedIterator<S, T>
        implements Iterator<T> {

    private final Iterator<S> iterator;
    private final ElTransformer<S, T> transformer;

    public TransformedIterator(Iterator<S> iterator, ElTransformer<S, T> transformer) {
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
