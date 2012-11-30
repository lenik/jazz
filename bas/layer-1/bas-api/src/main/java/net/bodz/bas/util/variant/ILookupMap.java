package net.bodz.bas.util.variant;

import java.util.Map;
import java.util.Set;

public interface ILookupMap<K, V> {

    Set<K> keySet();

    /**
     * @see Map#containsKey(Object)
     */
    boolean containsKey(Object key);

    /**
     * @see Map#get(Object)
     */
    V get(Object key);

}
