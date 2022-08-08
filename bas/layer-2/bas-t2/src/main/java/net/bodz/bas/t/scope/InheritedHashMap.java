package net.bodz.bas.t.scope;

import java.util.HashMap;
import java.util.Map;

public class InheritedHashMap<K, V>
        extends InheritedMap<K, V> {

    private static final long serialVersionUID = 9035648374269318014L;

    public InheritedHashMap(Map<K, V> pMap) {
        super(pMap);
    }

    @Override
    protected Map<K, V> createMap() {
        return new HashMap<K, V>();
    }

}
