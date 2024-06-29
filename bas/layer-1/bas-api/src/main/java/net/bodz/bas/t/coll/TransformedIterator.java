package net.bodz.bas.t.coll;

import java.util.Iterator;
import java.util.function.Function;

public class TransformedIterator<S, T>
        implements
            Iterator<T> {

    final Iterator<? extends S> source;
    final Function<S, T> transformer;

    public TransformedIterator(Iterator<? extends S> source, Function<S, T> transformer) {
        this.source = source;
        this.transformer = transformer;
    }

    @Override
    public boolean hasNext() {
        return source.hasNext();
    }

    @Override
    public T next() {
        S src = source.next();
        T dst = transformer.apply(src);
        return dst;
    }

    @Override
    public void remove() {
        source.remove();
    }

    public static <S, T> Iterator<T> transform(Iterator<? extends S> iterator, Function<S, T> transformer) {
        return new TransformedIterator<S, T>(iterator, transformer);
    }

}
