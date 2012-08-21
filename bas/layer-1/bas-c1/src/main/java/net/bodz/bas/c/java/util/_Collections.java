package net.bodz.bas.c.java.util;

import java.util.*;
import java.util.Collections;

/**
 * Because {@link java.util.Collections} isn't inheritable, so we have this class.
 */
public class _Collections {

    public <T extends Comparable<? super T>> void sort(List<T> list) {
        Collections.sort(list);
    }

    public <T> void sort(List<T> list, Comparator<? super T> c) {
        Collections.sort(list, c);
    }

    public <T> int binarySearch(List<? extends Comparable<? super T>> list, T key) {
        return Collections.binarySearch(list, key);
    }

    public <T> int binarySearch(List<? extends T> list, T key, Comparator<? super T> c) {
        return Collections.binarySearch(list, key, c);
    }

    public void reverse(List<?> list) {
        Collections.reverse(list);
    }

    public void shuffle(List<?> list) {
        Collections.shuffle(list);
    }

    public void shuffle(List<?> list, Random rnd) {
        Collections.shuffle(list, rnd);
    }

    public void swap(List<?> list, int i, int j) {
        Collections.swap(list, i, j);
    }

    public <T> void fill(List<? super T> list, T obj) {
        Collections.fill(list, obj);
    }

    public <T> void copy(List<? super T> dest, List<? extends T> src) {
        Collections.copy(dest, src);
    }

    public <T extends Object & Comparable<? super T>> T min(Collection<? extends T> coll) {
        return Collections.min(coll);
    }

    public <T> T min(Collection<? extends T> coll, Comparator<? super T> comp) {
        return Collections.min(coll, comp);
    }

    public <T extends Object & Comparable<? super T>> T max(Collection<? extends T> coll) {
        return Collections.max(coll);
    }

    public <T> T max(Collection<? extends T> coll, Comparator<? super T> comp) {
        return Collections.max(coll, comp);
    }

    public void rotate(List<?> list, int distance) {
        Collections.rotate(list, distance);
    }

    public <T> boolean replaceAll(List<T> list, T oldVal, T newVal) {
        return Collections.replaceAll(list, oldVal, newVal);
    }

    public int indexOfSubList(List<?> source, List<?> target) {
        return Collections.indexOfSubList(source, target);
    }

    public int lastIndexOfSubList(List<?> source, List<?> target) {
        return Collections.lastIndexOfSubList(source, target);
    }

    public <T> Collection<T> unmodifiableCollection(Collection<? extends T> c) {
        return Collections.unmodifiableCollection(c);
    }

    public <T> Set<T> unmodifiableSet(Set<? extends T> s) {
        return Collections.unmodifiableSet(s);
    }

    public <T> SortedSet<T> unmodifiableSortedSet(SortedSet<T> s) {
        return Collections.unmodifiableSortedSet(s);
    }

    public <T> List<T> unmodifiableList(List<? extends T> list) {
        return Collections.unmodifiableList(list);
    }

    public <K, V> Map<K, V> unmodifiableMap(Map<? extends K, ? extends V> m) {
        return Collections.unmodifiableMap(m);
    }

    public <K, V> SortedMap<K, V> unmodifiableSortedMap(SortedMap<K, ? extends V> m) {
        return Collections.unmodifiableSortedMap(m);
    }

    public <T> Collection<T> synchronizedCollection(Collection<T> c) {
        return Collections.synchronizedCollection(c);
    }

    public <T> Set<T> synchronizedSet(Set<T> s) {
        return Collections.synchronizedSet(s);
    }

    public <T> SortedSet<T> synchronizedSortedSet(SortedSet<T> s) {
        return Collections.synchronizedSortedSet(s);
    }

    public <T> List<T> synchronizedList(List<T> list) {
        return Collections.synchronizedList(list);
    }

    public <K, V> Map<K, V> synchronizedMap(Map<K, V> m) {
        return Collections.synchronizedMap(m);
    }

    public <K, V> SortedMap<K, V> synchronizedSortedMap(SortedMap<K, V> m) {
        return Collections.synchronizedSortedMap(m);
    }

    public <E> Collection<E> checkedCollection(Collection<E> c, Class<E> type) {
        return Collections.checkedCollection(c, type);
    }

    public <E> Set<E> checkedSet(Set<E> s, Class<E> type) {
        return Collections.checkedSet(s, type);
    }

    public <E> SortedSet<E> checkedSortedSet(SortedSet<E> s, Class<E> type) {
        return Collections.checkedSortedSet(s, type);
    }

    public <E> List<E> checkedList(List<E> list, Class<E> type) {
        return Collections.checkedList(list, type);
    }

    public <K, V> Map<K, V> checkedMap(Map<K, V> m, Class<K> keyType, Class<V> valueType) {
        return Collections.checkedMap(m, keyType, valueType);
    }

    public <K, V> SortedMap<K, V> checkedSortedMap(SortedMap<K, V> m, Class<K> keyType, Class<V> valueType) {
        return Collections.checkedSortedMap(m, keyType, valueType);
    }

    public <T> Iterator<T> emptyIterator() {
        return Collections.emptyIterator();
    }

    public <T> ListIterator<T> emptyListIterator() {
        return Collections.emptyListIterator();
    }

    public <T> Enumeration<T> emptyEnumeration() {
        return Collections.emptyEnumeration();
    }

    public <T> Set<T> emptySet() {
        return Collections.emptySet();
    }

    public <T> List<T> emptyList() {
        return Collections.emptyList();
    }

    public <K, V> Map<K, V> emptyMap() {
        return Collections.emptyMap();
    }

    public <T> Set<T> singleton(T o) {
        return Collections.singleton(o);
    }

    public <T> List<T> singletonList(T o) {
        return Collections.singletonList(o);
    }

    public <K, V> Map<K, V> singletonMap(K key, V value) {
        return Collections.singletonMap(key, value);
    }

    public <T> List<T> nCopies(int n, T o) {
        return Collections.nCopies(n, o);
    }

    public <T> Comparator<T> reverseOrder() {
        return Collections.reverseOrder();
    }

    public <T> Comparator<T> reverseOrder(Comparator<T> cmp) {
        return Collections.reverseOrder(cmp);
    }

    public <T> Enumeration<T> enumeration(Collection<T> c) {
        return Collections.enumeration(c);
    }

    public <T> ArrayList<T> list(Enumeration<T> e) {
        return Collections.list(e);
    }

    public int frequency(Collection<?> c, Object o) {
        return Collections.frequency(c, o);
    }

    public boolean disjoint(Collection<?> c1, Collection<?> c2) {
        return Collections.disjoint(c1, c2);
    }

    @SafeVarargs
    public final <T> boolean addAll(Collection<? super T> c, T... elements) {
        return Collections.addAll(c, elements);
    }

    public <E> Set<E> newSetFromMap(Map<E, Boolean> map) {
        return Collections.newSetFromMap(map);
    }

    public <T> Queue<T> asLifoQueue(Deque<T> deque) {
        return Collections.asLifoQueue(deque);
    }

}