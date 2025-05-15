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

    default boolean isEmptyPurged() {
        return true;
    }

    default boolean purge(Object keyToSet) {
        if (isEmptyPurged())
            return purge(keyToSet, get(keyToSet));
        else
            return false;
    }

    default boolean purge(Object keyToSet, Set<E> set) {
        if (isEmptyPurged())
            if (set != null) {
                if (set.isEmpty()) {
                    remove(keyToSet);
                    return true;
                }
            } else {
                if (containsKey(keyToSet)) {
                    remove(keyToSet);
                    return true;
                }
            }
        return false;
    }

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

    default E getFirstOfSet(Object keyToSet) {
        Set<E> set = get(keyToSet);
        if (set != null)
            for (E el : set)
                return el;
        return null;
    }

    default E getLastOfSet(Object keyToSet) {
        Set<E> set = get(keyToSet);
        E last = null;
        if (set != null)
            for (E el : set)
                last = el;
        return last;
    }

    default boolean addToSet(K keyToSet, E element) {
        Set<E> set = makeSet(keyToSet);
        return set.add(element);
    }

    default boolean removeFromSet(Object keyToSet, E element) {
        Set<E> set = get(keyToSet);
        if (set == null)
            return false;
        boolean any = set.remove(element);
        purge(keyToSet, set);
        return any;
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
            if (set != null) {
                set.removeAll(m.get(keyToSet));
                purge(keyToSet, set);
            }
        }
    }

    default void clearSets() {
        if (isEmptyPurged())
            clear();
        else
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
        for (Entry<K, Set<E>> entry : entrySet()) {
            Set<E> set = entry.getValue();
            if (set.remove(element)) {
                purge(entry.getKey(), set);
                count++;
            }
        }
        return count;
    }

    default boolean removeFromAnySets(E element) {
        for (Entry<K, Set<E>> entry : entrySet()) {
            Set<E> set = entry.getValue();
            if (set.remove(element)) {
                purge(entry.getKey(), set);
                return true;
            }
        }
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
