package net.bodz.bas.t.map;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.c.java.util.Collections;
import net.bodz.bas.meta.decl.NotNull;

public interface IListMap<K, E>
        extends Map<K, List<E>> {

    @NotNull
    List<E> makeList(K keyToList);

    @NotNull
    default List<E> getOrEmpty(K keyToList) {
        List<E> list = get(keyToList);
        if (list != null)
            return list;
        else
            return Collections.emptyList();
    }

    default boolean isEmptyPurged() {
        return true;
    }

    default boolean purge(Object keyToList) {
        if (isEmptyPurged())
            return purge(keyToList, get(keyToList));
        else
            return false;
    }

    default boolean purge(Object keyToList, List<E> list) {
        if (isEmptyPurged())
            if (list != null) {
                if (list.isEmpty()) {
                    remove(keyToList);
                    return true;
                }
            } else {
                if (containsKey(keyToList)) {
                    remove(keyToList);
                    return true;
                }
            }
        return false;
    }

    default long sizeOfAllLists() {
        long sum = 0;
        for (List<E> list : values())
            sum += list.size();
        return sum;
    }

    default boolean isAllListsEmpty() {
        for (List<E> list : values())
            if (!list.isEmpty())
                return false;
        return true;
    }

    default boolean isAnyListEmpty() {
        for (List<E> list : values())
            if (list.isEmpty())
                return true;
        return false;
    }

    default boolean isAllListsContains(E element) {
        for (List<E> list : values())
            if (!list.contains(element))
                return false;
        return true;
    }

    default boolean isAnyListContains(E element) {
        for (List<E> list : values())
            if (list.contains(element))
                return true;
        return false;
    }

    default boolean isListEmpty(K keyToList) {
        List<E> list = get(keyToList);
        return list == null || list.isEmpty();
    }

    default int sizeOfList(K keyToList) {
        List<E> list = get(keyToList);
        return list == null ? 0 : list.size();
    }

    default E getFirstOfList(Object keyToList) {
        List<E> list = get(keyToList);
        if (list == null || list.isEmpty())
            return null;
        else
            return list.get(list.size() - 1);
    }

    default E getLastOfList(Object keyToList) {
        List<E> list = get(keyToList);
        if (list == null || list.isEmpty())
            return null;
        else
            return list.get(list.size() - 1);
    }

    default E getFromList(Object keyToList, int listIndex) {
        List<E> list = get(keyToList);
        if (list == null)
            return null;
        else
            return list.get(listIndex);
    }

    default E setToList(K keyToList, int listIndex, E element) {
        List<E> list = get(keyToList);
        if (list == null)
            throw new IndexOutOfBoundsException("index " + listIndex + " to non-exist list.");
        return list.set(listIndex, element);
    }

    default boolean addToList(K keyToList, E element) {
        List<E> list = makeList(keyToList);
        return list.add(element);
    }

    default E removeFromList(Object keyToList, int listIndex) {
        List<E> list = get(keyToList);
        if (list == null)
            return null;
        E el = list.remove(listIndex);
        purge(keyToList, list);
        return el;
    }

    default boolean removeFromList(Object keyToList, Object element) {
        List<E> list = get(keyToList);
        if (list == null)
            return false;
        boolean any = list.remove(element);
        purge(keyToList, list);
        return any;
    }

    default void addAllToLists(Map<? extends K, ? extends List<E>> m) {
        for (K keyToList : m.keySet()) {
            List<E> list = makeList(keyToList);
            list.addAll(m.get(keyToList));
        }
    }

    default void removeAllFromLists(Map<? extends K, ? extends List<E>> m) {
        for (K keyToList : m.keySet()) {
            List<E> list = get(keyToList);
            if (list != null) {
                list.removeAll(m.get(keyToList));
                purge(keyToList, list);
            }
        }
    }

    default void clearLists() {
        if (isEmptyPurged())
            clear();
        else
            for (List<E> list : values())
                list.clear();
    }

    default List<E> concatenateAllLists() {
        List<E> concat = new ArrayList<>();
        for (List<E> list : values())
            concat.addAll(list);
        return concat;
    }

    default int removeFromAllLists(E element) {
        int count = 0;
        for (Entry<K, List<E>> entry : entrySet()) {
            List<E> list = entry.getValue();
            if (list.remove(element)) {
                purge(entry.getKey(), list);
                count++;
            }
        }
        return count;
    }

    default boolean removeFromAnyLists(Object element) {
        for (Entry<K, List<E>> entry : entrySet()) {
            List<E> list = entry.getValue();
            if (list.remove(element)) {
                purge(entry.getKey(), list);
                return true;
            }
        }
        return false;
    }

    default K findFirstKeyToListContains(E element) {
        for (Map.Entry<K, List<E>> entry : entrySet()) {
            List<E> list = entry.getValue();
            if (list.contains(element)) {
                return entry.getKey();
            }
        }
        return null;
    }

    default Set<K> findKeysToListsContain(E element) {
        Set<K> keys = new LinkedHashSet<>();
        for (Map.Entry<K, List<E>> entry : entrySet()) {
            List<E> list = entry.getValue();
            if (list.contains(element)) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

}
