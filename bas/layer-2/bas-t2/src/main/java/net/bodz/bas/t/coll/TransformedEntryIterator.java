package net.bodz.bas.t.coll;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.function.Function;

public class TransformedEntryIterator<K, S, T>
        implements
            Iterator<Entry<K, T>> {

    final Iterator<? extends Entry<K, S>> source;
    final Function<S, T> transformer;

    public TransformedEntryIterator(Iterator<? extends Entry<K, S>> source, Function<S, T> transformer) {
        this.source = source;
        this.transformer = transformer;
    }

    @Override
    public boolean hasNext() {
        return source.hasNext();
    }

    @Override
    public Entry<K, T> next() {
        Entry<K, S> src = source.next();
        return TransformedEntry.transform(src, transformer);
    }

    @Override
    public void remove() {
        source.remove();
    }

    public static <K, S, T> Iterator<Entry<K, T>> transform(Iterator<? extends Entry<K, S>> iterator,
            Function<S, T> transformer) {
        return new TransformedEntryIterator<K, S, T>(iterator, transformer);
    }

}
