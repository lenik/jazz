package net.bodz.bas.t.conv;

import java.util.Map.Entry;

public class EntryConverter<K, K_actual, V>
        implements
            IConverter<Entry<K, V>, Entry<K_actual, V>> {

    IConverter<K, K_actual> keyConverter;

    public EntryConverter(IConverter<K, K_actual> keyConverter) {
        this.keyConverter = keyConverter;
    }

    @Override
    public Entry<K_actual, V> convert(Entry<K, V> src) {
        return new RConvEntry<K, K_actual, V>(keyConverter, src);
    }

    @Override
    public Entry<K, V> restore(Entry<K_actual, V> dest) {
        return new ConvEntry<K, K_actual, V>(keyConverter, dest);
    }

}
