package net.bodz.bas.t.coll;

import java.util.Iterator;
import java.util.function.Function;

public class TransformedIterable<S, T>
        implements
            Iterable<T> {

    final Iterable<? extends S> orig;
    final Function<S, T> transformer;

    public TransformedIterable(Iterable<? extends S> orig, Function<S, T> transformer) {
        this.orig = orig;
        this.transformer = transformer;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<? extends S> iterator = orig.iterator();
        return new TransformedIterator<S, T>(iterator, transformer);
    }

}