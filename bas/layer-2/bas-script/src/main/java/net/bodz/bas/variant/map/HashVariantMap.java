package net.bodz.bas.variant.map;

import java.util.HashMap;
import java.util.Map;

public class HashVariantMap<K>
        extends Map2VariantMap<K> {

    public HashVariantMap() {
        super(new HashMap<K, Object>());
    }

    public HashVariantMap(int initialCapacity, float loadFactor) {
        super(new HashMap<K, Object>(initialCapacity, loadFactor));
    }

    public HashVariantMap(int initialCapacity) {
        super(new HashMap<K, Object>(initialCapacity));
    }

    public HashVariantMap(Map<? extends K, ?> m) {
        super(new HashMap<K, Object>(m));
    }

}
