package net.bodz.bas.i18n.nls_2;

import java.util.Set;

public interface ILookupMap<K, V> {

    Set<K> keySet();

    boolean containsKey(K key);

    V get(K key);

}
