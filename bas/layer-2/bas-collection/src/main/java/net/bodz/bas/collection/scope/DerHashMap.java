package net.bodz.bas.collection.scope;

import java.util.HashMap;
import java.util.Map;

public class DerHashMap<K, V> extends DerMap<K, V> {

    private static final long serialVersionUID = 9035648374269318014L;

    public DerHashMap(Map<K, V> pMap) {
        super(pMap);
    }

    @Override
    protected Map<K, V> createMap() {
        return new HashMap<K, V>();
    }

}
