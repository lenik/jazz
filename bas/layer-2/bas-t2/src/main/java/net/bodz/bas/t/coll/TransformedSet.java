package net.bodz.bas.t.coll;

import java.lang.reflect.Array;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import net.bodz.bas.err.NotImplementedException;

public class TransformedSet<S, T>
        extends AbstractSet<T>
        implements
            Set<T> {

    Set<S> source;
    Function<S, T> transformer;

    Class<T> targetType;
    Function<T, S> untransformer;

    public TransformedSet(Set<S> source, Function<S, T> transformer, Class<T> targetType,
            Function<T, S> untransformer) {
        this.source = source;
        this.transformer = transformer;
        this.targetType = targetType;
        this.untransformer = untransformer;
    }

    public TransformedSet(Set<S> source, Function<S, T> transformer, Class<T> targetType) {
        this.source = source;
        this.transformer = transformer;
        this.targetType = targetType;
        this.untransformer = (a) -> {
            throw new NotImplementedException();
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        source.forEach((s) -> action.accept(transformer.apply(s)));
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
        return TransformedIterator.transform(source.iterator(), transformer);
    }

    @Override
    public Object[] toArray() {
        @SuppressWarnings("unchecked")
        S[] sv = (S[]) source.toArray();
        Object tv = Array.newInstance(targetType, sv.length);
        for (int i = 0; i < sv.length; i++) {
            T t = transformer.apply(sv[i]);
            Array.set(tv, i, t);
        }
        return (Object[]) tv;
    }

    @Override
    public <_T> _T[] toArray(_T[] a) {
        @SuppressWarnings("unchecked")
        S[] sv = (S[]) source.toArray();

        Class<?> componentType = a.getClass().getComponentType();
        @SuppressWarnings("unchecked")
        _T[] tv = (_T[]) Array.newInstance(componentType, sv.length);

        for (int i = 0; i < sv.length; i++) {
            S s = sv[i];
            T t = transformer.apply(s);
            Array.set(tv, i, t);
        }
        return tv;
    }

    @Override
    public boolean add(T e) {
        return source.add(untransformer.apply(e));
    }

    @Override
    public boolean remove(Object o) {
        if (targetType.isInstance(o)) {
            T t = targetType.cast(o);
            S s = untransformer.apply(t);
            return source.remove(s);
        }
        return false;
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

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        return source.removeIf((s) -> filter.test(transformer.apply(s)));
    }

    public static <S, T> TransformedSet<S, T> transform(Set<S> source, Function<S, T> transformer,
            Class<T> targetType) {
        return new TransformedSet<S, T>(source, transformer, targetType);
    }

    public static <S, T> TransformedSet<S, T> transform(Set<S> source, Function<S, T> transformer, Class<T> targetType,
            Function<T, S> untransformer) {
        return new TransformedSet<S, T>(source, transformer, targetType, untransformer);
    }

}
