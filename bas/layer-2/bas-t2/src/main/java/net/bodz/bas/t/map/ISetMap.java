package net.bodz.bas.t.map;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.set.ArraySet;

public interface ISetMap<K, E>
        extends Map<K, Set<E>> {

    @NotNull
    Set<E> makeSet(K keyToSet);

    default long sizeOfAllSets() {
        long sum = 0;
        for (Set<E> set : values())
            sum += set.size();
        return sum;
    }

    default boolean isAllSetsEmpty() {
        for (Set<E> set : values())
            if (!set.isEmpty())
                return false;
        return true;
    }

    default boolean isAnySetEmpty() {
        for (Set<E> set : values())
            if (set.isEmpty())
                return true;
        return false;
    }

    default boolean isAllSetsContains(E element) {
        for (Set<E> set : values())
            if (!set.contains(element))
                return false;
        return true;
    }

    default boolean isAnySetContains(E element) {
        for (Set<E> set : values())
            if (set.contains(element))
                return true;
        return false;
    }

    default boolean isSetEmpty(K keyToSet) {
        Set<E> set = get(keyToSet);
        return set == null || set.isEmpty();
    }

    default int sizeOfSet(K keyToSet) {
        Set<E> set = get(keyToSet);
        return set == null ? 0 : set.size();
    }

    default boolean addToSet(K keyToSet, E element) {
        Set<E> set = makeSet(keyToSet);
        return set.add(element);
    }

    default boolean removeFromSet(Object keyToSet, E element) {
        Set<E> set = get(keyToSet);
        if (set == null)
            return false;
        return set.remove(element);
    }

    default void addAllToSets(Map<? extends K, ? extends Set<E>> m) {
        for (K keyToSet : m.keySet()) {
            Set<E> set = makeSet(keyToSet);
            set.addAll(m.get(keyToSet));
        }
    }

    default void removeAllFromSets(Map<? extends K, ? extends Set<E>> m) {
        for (K keyToSet : m.keySet()) {
            Set<E> set = get(keyToSet);
            if (set != null)
                set.removeAll(m.get(keyToSet));
        }
    }

    default void clearSets() {
        for (Set<E> set : values())
            set.clear();
    }

    default Set<E> concatenateAllSets() {
        Set<E> concat = new ArraySet<>();
        for (Set<E> set : values())
            concat.addAll(set);
        return concat;
    }

    default int removeFromAllSets(E element) {
        int count = 0;
        for (Set<E> set : values())
            if (set.remove(element))
                count++;
        return count;
    }

    default boolean removeFromAnySets(E element) {
        for (Set<E> set : values())
            if (set.remove(element))
                return true;
        return false;
    }

    default K findFirstKeyToSetContains(E element) {
        for (Entry<K, Set<E>> entry : entrySet()) {
            Set<E> set = entry.getValue();
            if (set.contains(element)) {
                return entry.getKey();
            }
        }
        return null;
    }

    default Set<K> findKeysToSetsContain(E element) {
        Set<K> keys = new LinkedHashSet<>();
        for (Entry<K, Set<E>> entry : entrySet()) {
            Set<E> set = entry.getValue();
            if (set.contains(element)) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

}
