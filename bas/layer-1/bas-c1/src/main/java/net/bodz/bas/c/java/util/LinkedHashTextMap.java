package net.bodz.bas.c.java.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashTextMap<V>
        extends LinkedHashMap<String, V>
        implements TextMap<V> {

    private static final long serialVersionUID = 1L;

    public LinkedHashTextMap() {
        super();
    }

    public LinkedHashTextMap(int initialCapacity, float loadFactor, boolean accessOrder) {
        super(initialCapacity, loadFactor, accessOrder);
    }

    public LinkedHashTextMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public LinkedHashTextMap(int initialCapacity) {
        super(initialCapacity);
    }

    public LinkedHashTextMap(Map<? extends String, ? extends V> m) {
        super(m);
    }

}
