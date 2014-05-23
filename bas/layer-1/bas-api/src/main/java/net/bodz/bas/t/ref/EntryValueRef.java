package net.bodz.bas.t.ref;

import java.util.Map.Entry;

public class EntryValueRef<V>
        extends ReadOnlyRef<V> {

    private final Entry<?, V> entry;
    private final Class<? extends V> valueType;

    public EntryValueRef(Entry<?, V> entry, Class<V> valueType) {
        if (entry == null)
            throw new NullPointerException("entry");
        if (valueType == null)
            throw new NullPointerException("valueType");
        this.entry = entry;
        this.valueType = valueType;
    }

    @Override
    public Class<? extends V> getValueType() {
        return valueType;
    }

    @Override
    public V get() {
        return entry.getValue();
    }

    @Override
    public void set(V value) {
        entry.setValue(value);
    }

}
