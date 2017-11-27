package net.bodz.bas.t.variant;

import java.util.Collections;
import java.util.Set;

public class NullLookupMap<K, V>
        implements ILookupMap<K, V> {

    @Override
    public Set<K> keySet() {
        return Collections.emptySet();
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public V get(Object key) {
        return null;
    }

}
