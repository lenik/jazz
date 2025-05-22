package net.bodz.bas.make.util;

import java.util.Map;
import java.util.Objects;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.proxy.java.util.DecoratedMap;
import net.bodz.bas.repr.form.SortOrder;

public class KeyDataMap<K, T>
        extends DecoratedMap<K, IKeyData<K, T>> {

    public KeyDataMap() {
        this(SortOrder.KEEP);
    }

    public KeyDataMap(SortOrder order) {
        this(order.newMapDefault());
    }

    public KeyDataMap(Map<K, IKeyData<K, T>> _orig) {
        super(_orig);
    }

    @Deprecated
    @Override
    public IKeyData<K, T> get(Object key) {
        return super.get(key);
    }

    @Deprecated
    @Override
    public IKeyData<K, T> put(K key, IKeyData<K, T> value) {
        return super.put(key, value);
    }

    public <UK extends K> IKeyData<UK, T> getTyped(UK key) {
        IKeyData<K, T> data = super.get(key);
        @SuppressWarnings("unchecked")
        IKeyData<UK, T> data2 = (IKeyData<UK, T>) data;
        return data2;
    }

    public <UK extends K> IKeyData<UK, T> putTyped(UK key, IKeyData<UK, T> value) {
        @SuppressWarnings("unchecked")
        IKeyData<K, T> value1 = (IKeyData<K, T>) value;

        IKeyData<K, T> old1 = super.put(key, value1);

        @SuppressWarnings("unchecked")
        IKeyData<UK, T> old2 = (IKeyData<UK, T>) old1;
        return old2;
    }

    public <UK extends K> IKeyData<UK, T> getTypedOrDefault(UK key, IKeyData<UK, T> defaultValue) {
        IKeyData<UK, T> value = getTyped(key);
        if (value != null || containsKey(key))
            return value;
        else
            return defaultValue;
    }

    public <UK extends K> boolean replaceTyped(UK key, IKeyData<UK, T> oldValue, IKeyData<UK, T> newValue) {
        IKeyData<UK, T> curValue = getTyped(key);
        if (!Objects.equals(curValue, oldValue) || (curValue == null && !containsKey(key))) {
            return false;
        }
        putTyped(key, newValue);
        return true;
    }

}
