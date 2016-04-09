package net.bodz.bas.ctx.util;

import java.util.Map;

import net.bodz.bas.err.NoSuchKeyException;

public interface IFramedMap<K, V>
        extends Map<K, V> {

    void enter();

    void leave();

    int getDepth();

    /**
     * @throws NoSuchKeyException
     *             If <code>key</code> isn't defined.
     */
    <T extends V> T _get(K key);

    <T extends V> T _get(K key, T defaultValue);

    V define(K key, V value);

    V define(K key, V value, boolean createLocal);

}
