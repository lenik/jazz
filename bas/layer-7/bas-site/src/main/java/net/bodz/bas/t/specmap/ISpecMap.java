package net.bodz.bas.t.specmap;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Specializable Map
 * <p>
 * A spec map is layered: the top layer is finer, the bottom layer is coarser.
 * <p>
 * In the bottom layer, you can define a value for range of keys, and in the top layer, you can define for only one key.
 * <p>
 * When querying the map, if a key is defined in the upper layer, it's override the key in the lower level. This is the
 * default behavior for the normal map functions like {@link Map#get(Object)} and {@link Map#containsKey(Object)}. If
 * you want to manipulate the specific layer in the map, you have to use the methods for the corresponding layer, like
 * <code>getRange</code>, <code>containsPattern</code>, <code>putDomain</code>, <code>removeIpMask</code>, etc.
 * <p>
 * explicit -> (range/pattern/... -> ) default
 */
public interface ISpecMap<key_t, val_t> {

    /**
     * find in all layers, from top down.
     */
    val_t find(key_t key);

    List<ILayerKeyValue<val_t>> findAll(key_t key);

    /**
     * @return <code>null</code> if not found.
     */
    SpecKeyLocation whichKey(key_t key);

    SpecLayer whichLayer(key_t key);

    /**
     * remove in specific layer.
     */
    ILayerKeyValue<val_t> removeFromLayer(SpecLayer layer, Object layerKey);

    ILayerKeyValue<val_t> removeFromAnyLayer(key_t key);

    // top layer

    Set<key_t> keySet();

    boolean containsKey(key_t key);

    val_t get(key_t key);

    val_t put(key_t key, val_t value);

    default boolean add(key_t key, val_t value) {
        if (containsKey(key))
            return false;
        put(key, value);
        return true;
    }

    default val_t getOrAdd(key_t key, val_t initial) {
        if (add(key, initial))
            return initial;
        else
            return get(key);
    }

    val_t remove(key_t key);

    void removeAllTops();

    // default layer

    boolean containsDefault();

    val_t getDefault();

    void setDefault(val_t value);

    default boolean addDefault(val_t value) {
        if (containsDefault())
            return false;
        setDefault(value);
        return true;
    }

    default val_t getOrAddDefault(val_t initial) {
        if (addDefault(initial))
            return initial;
        else
            return getDefault();
    }

    val_t removeDefault();

    void accept(ISpecMapVisitor<? super key_t, ? super val_t> visitor);

}