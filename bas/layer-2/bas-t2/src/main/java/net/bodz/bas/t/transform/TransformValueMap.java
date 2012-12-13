package net.bodz.bas.t.transform;

import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.t.util.CollectionToString;

public class TransformValueMap<K, V, _V>
        implements Map<K, V> {

    class EntryTransformer
            implements ElTransformer<Entry<K, V>, Entry<K, _V>> {

        @SuppressWarnings("unchecked")
        @Override
        public Class<Entry<K, V>> getUntransformedType() {
            return (Class<Entry<K, V>>) (Class<?>) Entry.class;
        }

        @SuppressWarnings("unchecked")
        @Override
        public Class<Entry<K, _V>> getTransformedType() {
            return (Class<Entry<K, _V>>) (Class<?>) Entry.class;
        }

        @Override
        public Entry<K, _V> transform(Entry<K, V> source) {
            K key = source.getKey();
            V sourceValue = source.getValue();
            _V transformedValue = transformer.transform(sourceValue);
            Entry<K, _V> transformedEntry = new SimpleEntry<K, _V>(key, transformedValue);
            return transformedEntry;
        }

        @Override
        public Entry<K, V> untransform(Entry<K, _V> transformed) {
            K key = transformed.getKey();
            _V transformedValue = transformed.getValue();
            V sourceValue = transformer.untransform(transformedValue);
            Entry<K, V> sourceEntry = new SimpleEntry<K, V>(key, sourceValue);
            return sourceEntry;
        }

    }

    private final ElTransformer<V, _V> transformer;
    private final ElTransformer<Entry<K, V>, Entry<K, _V>> entryTransformer;
    // private final Class<V> S_TYPE;
    // private final Class<_V> T_TYPE;
    private final Map<K, _V> map;

    public TransformValueMap(ElTransformer<V, _V> transformer, Map<K, _V> map) {
        if (transformer == null)
            throw new NullPointerException("transformer");
        if (map == null)
            throw new NullPointerException("map");
        this.transformer = transformer;
        this.entryTransformer = new EntryTransformer();
        this.map = map;
        // S_TYPE = transformer.getUntransformedType();
        // T_TYPE = transformer.getTransformedType();
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public void clear() {
        map.clear();
    }

    public Set<K> keySet() {
        return map.keySet();
    }

    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        // if (!S_TYPE.isInstance(value))
        // return false;
        // V source = S_TYPE.cast(value);
        @SuppressWarnings("unchecked")
        V source = (V) value;
        _V transformed = transformer.transform(source);
        return map.containsValue(transformed);
    }

    @Override
    public V get(Object key) {
        _V transformed = map.get(key);
        return transformer.untransform(transformed);
    }

    @Override
    public V put(K key, V value) {
        _V transformed = transformer.transform(value);
        _V previousTransformed = map.put(key, transformed);
        V previousValue = transformer.untransform(previousTransformed);
        return previousValue;
    }

    @Override
    public V remove(Object key) {
        _V previousTransformed = map.remove(key);
        V previousValue = transformer.untransform(previousTransformed);
        return previousValue;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> e : m.entrySet()) {
            K key = e.getKey();
            V value = e.getValue();
            _V transformed = transformer.transform(value);
            map.put(key, transformed);
        }
    }

    @Override
    public Collection<V> values() {
        Collection<_V> _values = map.values();
        return new UntransformCollection<V, _V>(transformer, _values);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, _V>> _entrySet = map.entrySet();
        return new UntransformSet<Entry<K, V>, Entry<K, _V>>(entryTransformer, _entrySet);
    }

    public int hashCode() {
        int hash = 0xc0bebf65;
        hash += map.hashCode();
        return hash;
    }

    public boolean equals(Object o) {
        if (o instanceof TransformValueMap<?, ?, ?>)
            return ((TransformValueMap<?, ?, ?>) o).map.equals(map);
        return false;
    }

    @Override
    public String toString() {
        return CollectionToString.toString(this);
    }

}
