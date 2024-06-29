package net.bodz.bas.t.coll;

import java.util.Enumeration;
import java.util.function.Function;

public class TransformedEnumeration<S, T>
        implements
            Enumeration<T> {

    Enumeration<? extends S> source;
    Function<S, T> transformer;

    public TransformedEnumeration(Enumeration<? extends S> source, Function<S, T> transformer) {
        if (source == null)
            throw new NullPointerException("source");
        if (transformer == null)
            throw new NullPointerException("transformer");
        this.source = source;
        this.transformer = transformer;
    }

    @Override
    public boolean hasMoreElements() {
        return source.hasMoreElements();
    }

    @Override
    public T nextElement() {
        S el = source.nextElement();
        T dest = transformer.apply(el);
        return dest;
    }

    public static <S, T> Enumeration<T> transform(Enumeration<? extends S> iterator, Function<S, T> transformer) {
        return new TransformedEnumeration<S, T>(iterator, transformer);
    }

}
