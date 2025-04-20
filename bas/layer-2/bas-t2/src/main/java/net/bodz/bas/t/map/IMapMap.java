package net.bodz.bas.t.map;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import net.bodz.bas.meta.decl.NotNull;

public interface IMapMap<K, EK, EV>
        extends Map<K, Map<EK, EV>> {

    @NotNull
    Map<EK, EV> makeMap(K keyToMap);

    default boolean isEmptyPurged() {
        return true;
    }

    default boolean purge(Object keyToMap) {
        if (isEmptyPurged())
            return purge(keyToMap, get(keyToMap));
        else
            return false;
    }

    default boolean purge(Object keyToMap, Map<EK, EV> map) {
        if (isEmptyPurged())
            if (map != null) {
                if (map.isEmpty()) {
                    remove(keyToMap);
                    return true;
                }
            } else {
                if (containsKey(keyToMap)) {
                    remove(keyToMap);
                    return true;
                }
            }
        return false;
    }

    default long sizeOfAllMaps() {
        long sum = 0;
        for (Map<EK, EV> map : values())
            sum += map.size();
        return sum;
    }

    default boolean isAllMapsEmpty() {
        for (Map<EK, EV> map : values())
            if (!map.isEmpty())
                return false;
        return true;
    }

    default boolean isAnyMapEmpty() {
        for (Map<EK, EV> map : values())
            if (map.isEmpty())
                return true;
        return false;
    }

    default boolean isAllMapsContains(EK key, EV value) {
        for (Map<EK, EV> map : values()) {
            if (!map.containsKey(key))
                return false;
            EV val = map.get(key);
            if (!Objects.equals(val, value))
                return false;
        }
        return true;
    }

    default boolean isAllMapsContainsKey(EK key) {
        for (Map<EK, EV> map : values())
            if (!map.containsKey(key))
                return false;
        return true;
    }

    default boolean isAllMapsContainsValue(EV value) {
        for (Map<EK, EV> map : values())
            if (!map.containsValue(value))
                return false;
        return true;
    }

    default boolean isAnyMapContainsKey(EK key, EV value) {
        for (Map<EK, EV> map : values())
            if (map.containsKey(key)) {
                EV val = map.get(key);
                if (Objects.equals(val, value))
                    return true;
            }
        return false;
    }

    default boolean isAnyMapContainsKey(EK key) {
        for (Map<EK, EV> map : values())
            if (map.containsKey(key))
                return true;
        return false;
    }

    default boolean isAnyMapContainsValue(EV value) {
        for (Map<EK, EV> map : values())
            if (map.containsValue(value))
                return true;
        return false;
    }

    default boolean isMapEmpty(K keyToMap) {
        Map<EK, EV> map = get(keyToMap);
        return map == null || map.isEmpty();
    }

    default int sizeOfMap(K keyToMap) {
        Map<EK, EV> map = get(keyToMap);
        return map == null ? 0 : map.size();
    }

    default EV getFromMap(Object keyToMap, EK key) {
        Map<EK, EV> map = get(keyToMap);
        if (map == null)
            return null;
        else
            return map.get(key);
    }

    default EV setToMap(K keyToMap, EK key, EV value) {
        Map<EK, EV> map = get(keyToMap);
        if (map == null)
            throw new IndexOutOfBoundsException("key " + key + " to non-exist map.");
        return map.put(key, value);
    }

    default EV putToMap(K keyToMap, EK key, EV value) {
        Map<EK, EV> map = makeMap(keyToMap);
        return map.put(key, value);
    }

    default EV removeFromMap(Object keyToMap, EK key) {
        Map<EK, EV> map = get(keyToMap);
        if (map == null)
            return null;
        EV val = map.remove(key);
        purge(keyToMap, map);
        return val;
    }

    default boolean removeFromMap(Object keyToMap, EK key, EV value) {
        Map<EK, EV> map = get(keyToMap);
        if (map == null)
            return false;
        boolean any = map.remove(key, value);
        purge(keyToMap, map);
        return any;
    }

    default boolean removeValueFromMap(Object keyToMap, EV value, boolean removeAll) {
        Map<EK, EV> map = get(keyToMap);
        if (map == null)
            return false;
        Iterator<Entry<EK, EV>> iterator = map.entrySet().iterator();
        int count = 0;
        boolean removeAny = !removeAll;
        while (iterator.hasNext()) {
            Entry<EK, EV> entry = iterator.next();
            if (Objects.equals(value, entry.getValue())) {
                iterator.remove();
                count++;
                if (removeAny)
                    break;
            }
        }
        boolean any = count != 0;
        if (any)
            purge(keyToMap, map);
        return any;
    }

    default void addAllToMaps(Map<? extends K, ? extends Map<EK, EV>> m) {
        for (K keyToMap : m.keySet()) {
            Map<EK, EV> map = makeMap(keyToMap);
            map.putAll(m.get(keyToMap));
        }
    }

    default boolean removeAllFromMaps(Map<? extends K, ? extends Map<EK, EV>> m) {
        int count = 0;
        for (K keyToMap : m.keySet()) {
            Map<EK, EV> sub = get(keyToMap);
            if (sub == null)
                continue;
            Map<EK, EV> mSub = m.get(keyToMap);
            // sub.removeAll(mSub);
            int batch = 0;
            for (Entry<EK, EV> mSubEntry : mSub.entrySet()) {
                if (sub.remove(mSubEntry.getKey(), mSubEntry.getValue()))
                    batch++;
            }
            if (batch != 0)
                purge(keyToMap, sub);
            count += batch;
        }
        return count != 0;
    }

    default void clearMaps() {
        if (isEmptyPurged())
            clear();
        else
            for (Map<EK, EV> map : values())
                map.clear();
    }

    default Map<EK, EV> union() {
        return union(new LinkedHashMap<>());
    }

    default Map<EK, EV> union(Map<EK, EV> union) {
        for (Map<EK, EV> map : values())
            union.putAll(map);
        return union;
    }

    default int removeFromAllMaps(EK key, EV value) {
        int count = 0;
        for (Entry<K, Map<EK, EV>> entry : entrySet()) {
            Map<EK, EV> map = entry.getValue();
            if (map.remove(key, value)) {
                purge(entry.getKey(), map);
                count++;
            }
        }
        return count;
    }

    default boolean removeFromAnyMaps(EK key, EV value) {
        for (Entry<K, Map<EK, EV>> entry : entrySet()) {
            Map<EK, EV> map = entry.getValue();
            if (map.remove(key, value)) {
                purge(entry.getKey(), map);
                return true;
            }
        }
        return false;
    }

    default K findFirstKeyToMapContains(EK key, EV value) {
        for (Entry<K, Map<EK, EV>> entry : entrySet()) {
            Map<EK, EV> map = entry.getValue();
            if (map.containsKey(key)) {
                EV val = map.get(key);
                if (Objects.equals(val, value))
                    return entry.getKey();
            }
        }
        return null;
    }

    default K findFirstKeyToMapContainsKey(EK key) {
        for (Entry<K, Map<EK, EV>> entry : entrySet()) {
            Map<EK, EV> map = entry.getValue();
            if (map.containsKey(key)) {
                return entry.getKey();
            }
        }
        return null;
    }

    default K findFirstKeyToMapContainsValue(EV value) {
        for (Entry<K, Map<EK, EV>> entry : entrySet()) {
            Map<EK, EV> map = entry.getValue();
            if (map.containsValue(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    default Set<K> findKeysToMapsContains(EK key, EV value) {
        Set<K> keys = new LinkedHashSet<>();
        for (Entry<K, Map<EK, EV>> entry : entrySet()) {
            Map<EK, EV> map = entry.getValue();
            if (map.containsKey(key)) {
                EV val = map.get(key);
                if (Objects.equals(val, value))
                    keys.add(entry.getKey());
            }
        }
        return keys;
    }

    default Set<K> findKeysToMapsContainsKey(EK key) {
        Set<K> keys = new LinkedHashSet<>();
        for (Entry<K, Map<EK, EV>> entry : entrySet()) {
            Map<EK, EV> map = entry.getValue();
            if (map.containsKey(key)) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    default Set<K> findKeysToMapsContainsValue(EV value) {
        Set<K> keys = new LinkedHashSet<>();
        for (Entry<K, Map<EK, EV>> entry : entrySet()) {
            Map<EK, EV> map = entry.getValue();
            if (map.containsValue(value)) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

}