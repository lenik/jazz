package net.bodz.bas.t.map;

import java.util.Map;

import net.bodz.bas.meta.decl.OfSameType;

@OfSameType
public interface IMapOst<K, V>
        extends Map<K, Map<K, V>> {

    boolean containsKey(K key1, K key2);

    Map<K, V> getOrCreate(K key1);

    V get(K key1, K key2);

    V put(K key1, K key2, V value);

    V remove(K key1, K key2);

    int size2();

}
