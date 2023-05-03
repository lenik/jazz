package net.bodz.bas.t.specmap;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Specializable Map
 * <p>
 * A spec map is layered: the top layer is finer, the bottom layer is coarser.
 * <p>
 * In the bottom layer, you can define a value for range of keys, and in the top layer, you can
 * define for only one key.
 * <p>
 * When querying the map, if a key is defined in the upper layer, it's override the key in the lower
 * level. This is the default behavior for the normal map functions like {@link Map#get(Object)} and
 * {@link Map#containsKey(Object)}. If you want to manipulate the specific layer in the map, you
 * have to use the methods for the corresponding layer, like <code>getRange</code>,
 * <code>containsPattern</code>, <code>putDomain</code>, <code>removeIpMask</code>, etc.
 * <p>
 * explicit -> (range/pattern/... -> ) default
 */
public interface ISpecMap<key_t, val_t>
        extends
            IDefaultHolder<val_t> {

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

    boolean hasTop();

    Set<key_t> topKeySet();

    boolean containsTop(key_t key);

    val_t getTop(key_t key);

    val_t putTop(key_t key, val_t value);

    default boolean addTop(key_t key, val_t value) {
        if (containsTop(key))
            return false;
        putTop(key, value);
        return true;
    }

    default val_t getOrAddTop(key_t key, val_t initial) {
        if (addTop(key, initial))
            return initial;
        else
            return getTop(key);
    }

    val_t removeTop(key_t key);

    void removeAllTops();

    void accept(ISpecMapVisitor<? super key_t, ? super val_t> visitor);

}