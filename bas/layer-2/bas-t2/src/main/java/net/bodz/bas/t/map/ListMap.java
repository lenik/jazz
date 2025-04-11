package net.bodz.bas.t.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.repr.form.SortOrder;

public class ListMap<K, E>
        implements Map<K, List<E>> {

    SortOrder order;
    Map<K, List<E>> map;

    public ListMap() {
        this(SortOrder.NONE);
    }

    public ListMap(SortOrder order) {
        this.order = order;
        this.map = createMap();
    }

    public SortOrder getOrder() {
        return order;
    }

    protected <key_t, value_t> Map<key_t, value_t> createMap() {
        return order.newMap();
    }

    protected <value_t> List<value_t> createList() {
        return new ArrayList<>();
    }

    public long sizeOfAllLists() {
        long sum = 0;
        for (List<E> list : map.values())
            sum += list.size();
        return sum;
    }

    public boolean isAllListsEmpty() {
        for (List<E> list : map.values())
            if (!list.isEmpty())
                return false;
        return true;
    }

    public boolean isAnyListEmpty() {
        for (List<E> list : map.values())
            if (list.isEmpty())
                return true;
        return false;
    }

    public boolean isAllListsContains(E listElement) {
        for (List<E> list : map.values())
            if (!list.contains(listElement))
                return false;
        return true;
    }

    public boolean isAnyListContains(E listElement) {
        for (List<E> list : map.values())
            if (list.contains(listElement))
                return true;
        return false;
    }

    public synchronized List<E> list(K keyToList) {
        List<E> list = map.get(keyToList);
        if (list == null) {
            list = createList();
            map.put(keyToList, list);
        }
        return list;
    }

    public E getInList(Object keyToList, int listIndex) {
        List<E> list = get(keyToList);
        if (list == null)
            return null;
        else
            return list.get(listIndex);
    }

    public E setInList(K keyToList, int listIndex, E listElement) {
        List<E> list = list(keyToList);
        return list.set(listIndex, listElement);
    }

    public boolean addToList(K keyToList, E listElement) {
        List<E> list = list(keyToList);
        return list.add(listElement);
    }

    public E removeFromList(Object keyToList, int listIndex) {
        List<E> list = get(keyToList);
        if (list == null)
            return null;
        return list.remove(listIndex);
    }

    public void addAllToLists(Map<? extends K, ? extends List<E>> m) {
        for (K keyToList : m.keySet()) {
            List<E> list = list(keyToList);
            list.addAll(m.get(keyToList));
        }
    }

    public void removeAllFromLists(Map<? extends K, ? extends List<E>> m) {
        for (K keyToList : m.keySet()) {
            List<E> list = get(keyToList);
            if (list != null)
                list.removeAll(m.get(keyToList));
        }
    }

    public void clearLists() {
        for (List<E> list : map.values())
            list.clear();
    }

    public List<E> concatenateAllLists() {
        List<E> concat = new ArrayList<>();
        for (List<E> list : values())
            concat.addAll(list);
        return concat;
    }

    public int removeFromAllLists(E item) {
        int count = 0;
        for (List<E> list : values())
            if (list.remove(item))
                count++;
        return count;
    }

    public boolean removeFromAnyLists(E item) {
        for (List<E> list : values())
            if (list.remove(item))
                return true;
        return false;
    }

    public K findFirstKeyToListContains(E item) {
        for (Map.Entry<K, List<E>> entry : map.entrySet()) {
            List<E> list = entry.getValue();
            if (list.contains(item)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public Set<K> findKeysToListsContain(E item) {
        Set<K> keys = new LinkedHashSet<>();
        for (Map.Entry<K, List<E>> entry : map.entrySet()) {
            List<E> list = entry.getValue();
            if (list.contains(item)) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    /** ⇱ Implementation Of {@link Map}. */
    /* _____________________________ */static section.iface __MAP__;

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public List<E> get(Object key) {
        return map.get(key);
    }

    @Override
    public List<E> put(K key, List<E> value) {
        return map.put(key, value);
    }

    @Override
    public List<E> remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends List<E>> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @NotNull
    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @NotNull
    @Override
    public Collection<List<E>> values() {
        return map.values();
    }

    @NotNull
    @Override
    public Set<Entry<K, List<E>>> entrySet() {
        return map.entrySet();
    }

}
