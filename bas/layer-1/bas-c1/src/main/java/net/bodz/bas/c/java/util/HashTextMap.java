package net.bodz.bas.c.java.util;

import java.util.HashMap;
import java.util.Map;

public class HashTextMap<V>
        extends HashMap<String, V>
        implements TextMap<V> {

    private static final long serialVersionUID = 1L;

    public HashTextMap() {
        super();
    }

    public HashTextMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public HashTextMap(int initialCapacity) {
        super(initialCapacity);
    }

    public HashTextMap(Map<? extends String, ? extends V> m) {
        super(m);
    }

}
