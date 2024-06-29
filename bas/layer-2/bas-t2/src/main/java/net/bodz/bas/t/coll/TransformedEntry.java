package net.bodz.bas.t.coll;

import java.util.Map.Entry;
import java.util.function.Function;

import net.bodz.bas.err.NotImplementedException;

public class TransformedEntry<K, S, T>
        implements
            Entry<K, T> {

    Entry<K, S> source;
    Function<S, T> transformer;

    Function<T, S> untransformer;

    public TransformedEntry(Entry<K, S> source, Function<S, T> transformer) {
        this.source = source;
        this.transformer = transformer;
        this.untransformer = (t) -> {
            throw new NotImplementedException();
        };
    }

    public TransformedEntry(Entry<K, S> source, Function<S, T> transformer, Function<T, S> untransformer) {
        this.source = source;
        this.transformer = transformer;
        this.untransformer = untransformer;
    }

    @Override
    public K getKey() {
        return source.getKey();
    }

    @Override
    public T getValue() {
        return transformer.apply(source.getValue());
    }

    @Override
    public T setValue(T value) {
        S s = untransformer.apply(value);
        S old = source.setValue(s);
        return transformer.apply(old);
    }

    public static <K, S, T> TransformedEntry<K, S, T> transform(Entry<K, S> source, Function<S, T> transformer) {
        return new TransformedEntry<K, S, T>(source, transformer);
    }

    public static <K, S, T> TransformedEntry<K, S, T> transform(Entry<K, S> source, Function<S, T> transformer,
            Function<T, S> untransformer) {
        return new TransformedEntry<K, S, T>(source, transformer, untransformer);
    }

}
