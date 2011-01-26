package net.bodz.bas.collection.preorder;

import java.util.HashMap;

/**
 * The {@link IPreorder#compare(Object, Object)} is not used.
 */
public abstract class PreorderMap<K, V>
        extends HashMap<K, V> {

    private static final long serialVersionUID = 1L;

    private final IPreorder<K> preorder;

    public PreorderMap(IPreorder<K> preorder) {
        if (preorder == null)
            throw new NullPointerException("preorder");
        this.preorder = preorder;
    }

    public K floorKey(K key) {
        while (!containsKey(key)) {
            key = preorder.getPreceding(key);
            if (key == null)
                return null;
        }
        return key;
    }

    public V floor(K key) {
        key = floorKey(key);
        return get(key);
    }

}
