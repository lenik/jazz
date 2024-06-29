package net.bodz.bas.t.transform;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Function;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.t.iterator.Iterators;

public class TransformedCollection<S, T>
        extends AbstractCollection<T>
        implements
            Collection<T> {

    Collection<S> source;
    Function<S, T> transformer;

    public TransformedCollection(Collection<S> source, Function<S, T> transformer) {
        if (source == null)
            throw new NullPointerException("source");
        if (transformer == null)
            throw new NullPointerException("transformer");
        this.source = source;
        this.transformer = transformer;
    }

    public static <S, T> TransformedCollection<S, T> transform(Collection<S> source, Function<S, T> transformer) {
        return new TransformedCollection<S, T>(source, transformer);
    }

    @Override
    public int size() {
        return source.size();
    }

    @Override
    public boolean isEmpty() {
        return source.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return source.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return Iterators.transform(source.iterator(), transformer);
    }

    @Override
    public Object[] toArray() {
        return source.toArray();
    }

    @Override
    public <_T> _T[] toArray(_T[] a) {
        return source.toArray(a);
    }

    @Override
    public boolean add(T e) {
        throw new NotImplementedException();
//        return source.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return source.remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return source.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return source.retainAll(c);
    }

    @Override
    public void clear() {
        source.clear();
    }

    @Override
    public boolean equals(Object o) {
        return source.equals(o);
    }

    @Override
    public int hashCode() {
        return source.hashCode();
    }

}
