package net.bodz.bas.t.conv;

import java.util.Map.Entry;

public class RConvEntry<K, K_actual, V>
        implements
            Entry<K_actual, V> {

    IConverter<K, K_actual> keyConverter;
    Entry<K, V> entry;

    public RConvEntry(IConverter<K, K_actual> keyConverter, Entry<K, V> entry) {
        this.keyConverter = keyConverter;
        this.entry = entry;
    }

    @Override
    public K_actual getKey() {
        K key = entry.getKey();
        return keyConverter.convert(key);
    }

    @Override
    public V getValue() {
        return entry.getValue();
    }

    @Override
    public V setValue(V value) {
        return entry.setValue(value);
    }

}
