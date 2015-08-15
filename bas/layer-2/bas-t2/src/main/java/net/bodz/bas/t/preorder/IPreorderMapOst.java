package net.bodz.bas.t.preorder;

import java.util.Map;
import java.util.Set;

import net.bodz.bas.meta.decl.OfSameType;
import net.bodz.bas.t.pojo.Pair;
import net.bodz.bas.t.pojo.Triple;

/**
 * @see net.bodz.bas.t.map.IMapOst
 */
@OfSameType
public interface IPreorderMapOst<K, V>
        extends IPreorderMap<K, IPreorderMap<K, V>>/* , IMapOst<K, V> */{

    boolean containsKey(K key1, K key2);

    Map<K, V> getOrCreate(K key1);

    V get(K key1, K key2);

    V put(K key1, K key2, V value);

    V remove(K key1, K key2);

    int size2();

    /**
     * @return <code>null</code> if no meet.
     */
    Triple<K, K, V> meetEntry(K key1, K key2);

    /**
     * @return <code>null</code> if no meet.
     */
    Pair<K, K> meetKey(K key1, K key2);

    V meet(K key1, K key2);

    Iterable<? extends Triple<K, K, V>> joinEntries(K key1, K key2);

    Iterable<? extends Pair<K, K>> joinKeys(K key1, K key2);

    Iterable<V> join(K key1, K key2);

    Map<Pair<K, K>, V> joinMap(K key1, K key2);

    Set<Pair<K, K>> joinKeySet(K key1, K key2);

    Set<V> joinValueSet(K key1, K key2);

}
