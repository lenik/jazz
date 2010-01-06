package net.bodz.bas.collection.iterator;

import java.util.Iterator;

import net.bodz.bas.collection.transform.ITransformer;

public class TransformedIterator<T>
        implements Iterator<T> {

    private final Iterator<T> iterator;
    private final ITransformer<T> transformer;

    public TransformedIterator(Iterator<T> iterator, ITransformer<T> transformer) {
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
        T item = iterator.next();
        return transformer.transform(item);
    }

    @Override
    public void remove() {
        iterator.remove();
    }

}
