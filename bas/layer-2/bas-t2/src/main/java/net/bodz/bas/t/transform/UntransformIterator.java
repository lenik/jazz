package net.bodz.bas.t.transform;

import java.util.Iterator;

public final class UntransformIterator<S, T>
        implements Iterator<S> {

    private final ElTransformer<S, T> transformer;
    private final Iterator<T> transformedIterator;

    public UntransformIterator(ElTransformer<S, T> transformer, Iterator<T> transformedIterator) {
        if (transformer == null)
            throw new NullPointerException("transformer");
        if (transformedIterator == null)
            throw new NullPointerException("transformedIterator");
        this.transformer = transformer;
        this.transformedIterator = transformedIterator;
    }

    @Override
    public boolean hasNext() {
        return transformedIterator.hasNext();
    }

    @Override
    public S next() {
        T transformed = transformedIterator.next();
        S source = transformer.unapply(transformed);
        return source;
    }

    @Override
    public void remove() {
        transformedIterator.remove();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UntransformIterator<?, ?>) {
            UntransformIterator<?, ?> o = (UntransformIterator<?, ?>) obj;
            if (!transformer.equals(o.transformer))
                return false;
            if (!transformedIterator.equals(o.transformedIterator))
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0x92961e60;
        hash ^= transformedIterator.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return "Untransform(" + transformedIterator.toString() + ")";
    }

}
