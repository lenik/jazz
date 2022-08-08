package net.bodz.bas.t.scope;

import java.util.TreeMap;

public class InheritedTreeMap<K, V>
        extends InheritedSortedMap<K, V> {

    private static final long serialVersionUID = 437251376108591491L;

    public InheritedTreeMap(TreeMap<K, V> pMap) {
        super(pMap);
    }

    @Override
    protected TreeMap<K, V> createMap() {
        return new TreeMap<K, V>(comparator());
    }

}
