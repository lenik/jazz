package net.bodz.bas.types;

import java.util.HashMap;
import java.util.Map;

public class HashTextMap<T> extends HashMap<String, T> implements TextMap<T> {

    private static final long serialVersionUID = 6592220579601421664L;

    public HashTextMap() {
        super();
    }

    public HashTextMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public HashTextMap(int initialCapacity) {
        super(initialCapacity);
    }

    public HashTextMap(Map<? extends String, ? extends T> m) {
        super(m);
    }

}
