package net.bodz.bas.t.coll;

import java.lang.reflect.Array;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import net.bodz.bas.err.NotImplementedException;

public class TransformedEntrySet<K, S, T>
        extends AbstractSet<Entry<K, T>>
        implements
            Set<Entry<K, T>> {

    Set<Entry<K, S>> source;
    Function<S, T> transformer;

    Class<T> targetType;
    Function<T, S> untransformer;

    public TransformedEntrySet(Set<Entry<K, S>> source, Function<S, T> transformer, Class<T> targetType,
            Function<T, S> untransformer) {
        this.source = source;
        this.transformer = transformer;
        this.targetType = targetType;
        this.untransformer = untransformer;
    }

    public TransformedEntrySet(Set<Entry<K, S>> source, Function<S, T> transformer, Class<T> targetType) {
        this.source = source;
        this.transformer = transformer;
        this.targetType = targetType;
        this.untransformer = (a) -> {
            throw new NotImplementedException();
        };
    }

    @Override
    public void forEach(Consumer<? super Entry<K, T>> action) {
        source.forEach((s) -> action.accept(TransformedEntry.transform(s, transformer)));
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

//    @Override
//    public Iterator<T> iterator() {
//        return TransformedIterator.transform(source.iterator(), transformer);
//    }
    @Override
    public Iterator<Entry<K, T>> iterator() {
        return TransformedEntryIterator.transform(source.iterator(), transformer);
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

//
//    @Override
//    public boolean add(T e) {
//        return source.add(untransformer.apply(e));
//    }
    @Override
    public boolean add(Entry<K, T> e) {
        return source.add(TransformedEntry.transform(e, untransformer));
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
    public boolean removeIf(Predicate<? super Entry<K, T>> filter) {
        return source.removeIf((s) -> filter.test(TransformedEntry.transform(s, transformer)));
    }

    public static <K, S, T> TransformedEntrySet<K, S, T> transform(Set<Entry<K, S>> source, Function<S, T> transformer,
            Class<T> targetType) {
        return new TransformedEntrySet<K, S, T>(source, transformer, targetType);
    }

    public static <K, S, T> TransformedEntrySet<K, S, T> transform(Set<Entry<K, S>> source, Function<S, T> transformer,
            Class<T> targetType, Function<T, S> untransformer) {
        return new TransformedEntrySet<K, S, T>(source, transformer, targetType, untransformer);
    }

}
