package net.bodz.bas.collection.override;

import java.util.TreeMap;

public class DerTreeMap<K, V> extends DerSortedMap<K, V> {

    private static final long serialVersionUID = 437251376108591491L;

    public DerTreeMap(TreeMap<K, V> pMap) {
        super(pMap);
    }

    @Override
    protected TreeMap<K, V> createMap() {
        return new TreeMap<K, V>(comparator());
    }

}
