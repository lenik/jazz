package net.bodz.bas.c.object;

import java.util.IdentityHashMap;
import java.util.Map;

public class IdentityObjectMap<V>
        extends IdentityHashMap<Object, V> {

    private static final long serialVersionUID = 1L;

    public IdentityObjectMap() {
        super();
    }

    public IdentityObjectMap(int expectedMaxSize) {
        super(expectedMaxSize);
    }

    public IdentityObjectMap(Map<? extends Object, ? extends V> m) {
        super(m);
    }

}
