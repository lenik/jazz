package net.bodz.bas.c.java.util;

import java.util.*;
import java.util.Collections;

/**
 * Because {@link java.util.Collections} isn't inheritable, so we have this class.
 */
public class _Collections {

    public static <T extends Comparable<? super T>> void sort(List<T> list) {
        Collections.sort(list);
    }

    public static <T> void sort(List<T> list, Comparator<? super T> c) {
        Collections.sort(list, c);
    }

    public static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key) {
        return Collections.binarySearch(list, key);
    }

    public static <T> int binarySearch(List<? extends T> list, T key, Comparator<? super T> c) {
        return Collections.binarySearch(list, key, c);
    }

    public static void reverse(List<?> list) {
        Collections.reverse(list);
    }

    public static void shuffle(List<?> list) {
        Collections.shuffle(list);
    }

    public static void shuffle(List<?> list, Random rnd) {
        Collections.shuffle(list, rnd);
    }

    public static void swap(List<?> list, int i, int j) {
        Collections.swap(list, i, j);
    }

    public static <T> void fill(List<? super T> list, T obj) {
        Collections.fill(list, obj);
    }

    public static <T> void copy(List<? super T> dest, List<? extends T> src) {
        Collections.copy(dest, src);
    }

    public static <T extends Object & Comparable<? super T>> T min(Collection<? extends T> coll) {
        return Collections.min(coll);
    }

    public static <T> T min(Collection<? extends T> coll, Comparator<? super T> comp) {
        return Collections.min(coll, comp);
    }

    public static <T extends Object & Comparable<? super T>> T max(Collection<? extends T> coll) {
        return Collections.max(coll);
    }

    public static <T> T max(Collection<? extends T> coll, Comparator<? super T> comp) {
        return Collections.max(coll, comp);
    }

    public static void rotate(List<?> list, int distance) {
        Collections.rotate(list, distance);
    }

    public static <T> boolean replaceAll(List<T> list, T oldVal, T newVal) {
        return Collections.replaceAll(list, oldVal, newVal);
    }

    public static int indexOfSubList(List<?> source, List<?> target) {
        return Collections.indexOfSubList(source, target);
    }

    public static int lastIndexOfSubList(List<?> source, List<?> target) {
        return Collections.lastIndexOfSubList(source, target);
    }

    public static <T> Collection<T> unmodifiableCollection(Collection<? extends T> c) {
        return Collections.unmodifiableCollection(c);
    }

    public static <T> Set<T> unmodifiableSet(Set<? extends T> s) {
        return Collections.unmodifiableSet(s);
    }

    public static <T> SortedSet<T> unmodifiableSortedSet(SortedSet<T> s) {
        return Collections.unmodifiableSortedSet(s);
    }

    public static <T> List<T> unmodifiableList(List<? extends T> list) {
        return Collections.unmodifiableList(list);
    }

    public static <K, V> Map<K, V> unmodifiableMap(Map<? extends K, ? extends V> m) {
        return Collections.unmodifiableMap(m);
    }

    public static <K, V> SortedMap<K, V> unmodifiableSortedMap(SortedMap<K, ? extends V> m) {
        return Collections.unmodifiableSortedMap(m);
    }

    public static <T> Collection<T> synchronizedCollection(Collection<T> c) {
        return Collections.synchronizedCollection(c);
    }

    public static <T> Set<T> synchronizedSet(Set<T> s) {
        return Collections.synchronizedSet(s);
    }

    public static <T> SortedSet<T> synchronizedSortedSet(SortedSet<T> s) {
        return Collections.synchronizedSortedSet(s);
    }

    public static <T> List<T> synchronizedList(List<T> list) {
        return Collections.synchronizedList(list);
    }

    public static <K, V> Map<K, V> synchronizedMap(Map<K, V> m) {
        return Collections.synchronizedMap(m);
    }

    public static <K, V> SortedMap<K, V> synchronizedSortedMap(SortedMap<K, V> m) {
        return Collections.synchronizedSortedMap(m);
    }

    public static <E> Collection<E> checkedCollection(Collection<E> c, Class<E> type) {
        return Collections.checkedCollection(c, type);
    }

    public static <E> Set<E> checkedSet(Set<E> s, Class<E> type) {
        return Collections.checkedSet(s, type);
    }

    public static <E> SortedSet<E> checkedSortedSet(SortedSet<E> s, Class<E> type) {
        return Collections.checkedSortedSet(s, type);
    }

    public static <E> List<E> checkedList(List<E> list, Class<E> type) {
        return Collections.checkedList(list, type);
    }

    public static <K, V> Map<K, V> checkedMap(Map<K, V> m, Class<K> keyType, Class<V> valueType) {
        return Collections.checkedMap(m, keyType, valueType);
    }

    public static <K, V> SortedMap<K, V> checkedSortedMap(SortedMap<K, V> m, Class<K> keyType, Class<V> valueType) {
        return Collections.checkedSortedMap(m, keyType, valueType);
    }

    public static <T> Iterator<T> emptyIterator() {
        return Collections.emptyIterator();
    }

    public static <T> ListIterator<T> emptyListIterator() {
        return Collections.emptyListIterator();
    }

    public static <T> Enumeration<T> emptyEnumeration() {
        return Collections.emptyEnumeration();
    }

    public static <T> Set<T> emptySet() {
        return Collections.emptySet();
    }

    public static <T> List<T> emptyList() {
        return Collections.emptyList();
    }

    public static <K, V> Map<K, V> emptyMap() {
        return Collections.emptyMap();
    }

    public static <T> Set<T> singleton(T o) {
        return Collections.singleton(o);
    }

    public static <T> List<T> singletonList(T o) {
        return Collections.singletonList(o);
    }

    public static <K, V> Map<K, V> singletonMap(K key, V value) {
        return Collections.singletonMap(key, value);
    }

    public static <T> List<T> nCopies(int n, T o) {
        return Collections.nCopies(n, o);
    }

    public static <T> Comparator<T> reverseOrder() {
        return Collections.reverseOrder();
    }

    public static <T> Comparator<T> reverseOrder(Comparator<T> cmp) {
        return Collections.reverseOrder(cmp);
    }

    public static <T> Enumeration<T> enumeration(Collection<T> c) {
        return Collections.enumeration(c);
    }

    public static <T> ArrayList<T> list(Enumeration<T> e) {
        return Collections.list(e);
    }

    public static int frequency(Collection<?> c, Object o) {
        return Collections.frequency(c, o);
    }

    public static boolean disjoint(Collection<?> c1, Collection<?> c2) {
        return Collections.disjoint(c1, c2);
    }

    @SafeVarargs
    public static final <T> boolean addAll(Collection<? super T> c, T... elements) {
        return Collections.addAll(c, elements);
    }

    public static <E> Set<E> newSetFromMap(Map<E, Boolean> map) {
        return Collections.newSetFromMap(map);
    }

    public static <T> Queue<T> asLifoQueue(Deque<T> deque) {
        return Collections.asLifoQueue(deque);
    }

}