package net.bodz.bas.t.ref;

import java.util.Map.Entry;

public class EntryKeyRef<K>
        extends ReadOnlyRef<K> {

    private final Entry<K, ?> entry;
    private final Class<? extends K> keyType;

    public EntryKeyRef(Entry<K, ?> entry, Class<K> keyType) {
        if (entry == null)
            throw new NullPointerException("entry");
        if (keyType == null)
            throw new NullPointerException("keyType");
        this.entry = entry;
        this.keyType = keyType;
    }

    @Override
    public Class<? extends K> getValueType() {
        return keyType;
    }

    @Override
    public K get() {
        return entry.getKey();
    }

}
