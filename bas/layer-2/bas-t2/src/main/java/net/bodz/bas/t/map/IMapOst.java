package net.bodz.bas.t.map;

import java.util.Map;

import net.bodz.bas.meta.decl.OfSameType;

@OfSameType
public interface IMapOst<K, V>
        extends Map<K, Map<K, V>> {

    boolean containsKey2(K key1, K key2);

    Map<K, V> getOrCreate2(K key1);

    V get2(K key1, K key2);

    V put2(K key1, K key2, V value);

    V remove2(K key1, K key2);

    int size2();

}
