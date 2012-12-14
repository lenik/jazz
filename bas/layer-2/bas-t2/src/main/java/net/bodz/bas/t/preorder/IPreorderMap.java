package net.bodz.bas.t.preorder;

import java.util.Map;

/**
 * @see IPreorder
 */
public interface IPreorderMap<K, V>
        extends Map<K, V> {

    Entry<K, V> meetEntry(K key);

    K meetKey(K key);

    V meet(K key);

    Iterable<Entry<K, V>> joinEntries(K key);

    Iterable<K> joinKeys(K key);

    Iterable<V> join(K key);

}