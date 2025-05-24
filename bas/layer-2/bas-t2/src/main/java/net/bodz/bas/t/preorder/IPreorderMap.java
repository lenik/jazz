package net.bodz.bas.t.preorder;

import java.util.Map;
import java.util.Set;

import net.bodz.bas.meta.decl.NotNull;

/**
 * @see IPreorder
 */
public interface IPreorderMap<K, V>
        extends Map<K, V> {

    @NotNull
    IPreorder<K> getPreorder();

    /**
     * @return <code>null</code> entry if no meet.
     */
    Entry<K, V> meetEntry(K key);

    K meetKey(K key);

    V meet(K key);

    @NotNull
    Iterable<Entry<K, V>> joinEntries(K key);

    @NotNull
    Iterable<K> joinKeys(K key);

    @NotNull
    Iterable<V> join(K key);

    @NotNull
    Map<K, V> joinMap(K key);

    @NotNull
    Set<K> joinKeySet(K key);

    @NotNull
    Set<V> joinValueSet(K key);

}
