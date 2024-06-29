package net.bodz.bas.t.coll;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import net.bodz.bas.err.NotImplementedException;

public class ValueTransformedMap<K, S, T>
        extends AbstractMap<K, T>
        implements
            Map<K, T> {

    Map<K, S> source;
    Function<S, T> transformer;

    Class<T> targetType;
    Function<T, S> untransformer;

    public ValueTransformedMap(Map<K, S> source, Function<S, T> transformer, Class<T> targetType,
            Function<T, S> untransformer) {
        this.source = source;
        this.transformer = transformer;
        this.targetType = targetType;
        this.untransformer = untransformer;
    }

    public ValueTransformedMap(Map<K, S> source, Function<S, T> transformer, Class<T> targetType) {
        this.source = source;
        this.transformer = transformer;
        this.targetType = targetType;
        this.untransformer = (a) -> {
            throw new NotImplementedException();
        };
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
    public boolean containsKey(Object key) {
        return source.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return source.containsValue(value);
    }

    @Override
    public T get(Object key) {
        return transformer.apply(source.get(key));
    }

    @Override
    public T put(K key, T value) {
        S s = source.put(key, untransformer.apply(value));
        return transformer.apply(s);
    }

    @Override
    public T remove(Object key) {
        S s = source.remove(key);
        return transformer.apply(s);
    }

    @Override
    public void clear() {
        source.clear();
    }

    @Override
    public Set<K> keySet() {
        return source.keySet();
    }

    @Override
    public Collection<T> values() {
        return TransformedCollection.transform(source.values(), transformer, targetType, untransformer);
    }

    @Override
    public Set<Entry<K, T>> entrySet() {
        return TransformedEntrySet.transform(source.entrySet(), //
                (s) -> transformer.apply(s), targetType);
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
    public boolean remove(Object key, Object value) {
        return source.remove(key, value);
    }

    public static <K, S, T> ValueTransformedMap<K, S, T> transform(Map<K, S> source, Function<S, T> transformer,
            Class<T> targetType) {
        return new ValueTransformedMap<K, S, T>(source, transformer, targetType);
    }

    public static <K, S, T> ValueTransformedMap<K, S, T> transform(Map<K, S> source, Function<S, T> transformer,
            Class<T> targetType, Function<T, S> untransformer) {
        return new ValueTransformedMap<K, S, T>(source, transformer, targetType, untransformer);
    }

}
