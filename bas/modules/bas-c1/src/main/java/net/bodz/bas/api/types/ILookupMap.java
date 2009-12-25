package net.bodz.bas.api.types;

import java.util.Set;

public interface ILookupMap<K, V> {

    Set<K> keySet();

    boolean containsKey(K key);

    V get(K key);

}
