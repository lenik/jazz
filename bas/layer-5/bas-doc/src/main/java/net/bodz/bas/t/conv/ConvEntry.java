package net.bodz.bas.t.conv;

import java.util.Map.Entry;

public class ConvEntry<K, K_actual, V>
        implements
            Entry<K, V> {

    IConverter<K, K_actual> keyConverter;
    Entry<K_actual, V> actualEntry;

    public ConvEntry(IConverter<K, K_actual> keyConverter, Entry<K_actual, V> actualEntry) {
        this.keyConverter = keyConverter;
        this.actualEntry = actualEntry;
    }

    @Override
    public K getKey() {
        K_actual ak = actualEntry.getKey();
        return keyConverter.restore(ak);
    }

    @Override
    public V getValue() {
        return actualEntry.getValue();
    }

    @Override
    public V setValue(V value) {
        return actualEntry.setValue(value);
    }

}
