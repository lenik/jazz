package net.bodz.bas.repr.form;

import java.util.*;

public enum SortOrder {

    NONE,

    SORTED,

    ASCENDING,

    DESCENDING,

    KEEP,

    ;

    public <K, V> Map<K, V> newMap() {
        return newMap(0, null);
    }

    public <K, V> Map<K, V> newMap(Comparator<? super K> cmp) {
        return newMap(0, cmp);
    }

    public <K, V> Map<K, V> newMap(int initialCapacity) {
        return newMap(initialCapacity, null);
    }

    public <K, V> Map<K, V> newMap(int initialCapacity, Comparator<? super K> cmp) {
        switch (this) {
        case SORTED:
        case ASCENDING:
            if (cmp == null)
                return new TreeMap<>();
            else
                return new TreeMap<>(cmp);

        case DESCENDING:
            if (cmp == null)
                return new TreeMap<>(Collections.reverseOrder());
            else
                return new TreeMap<>(Collections.reverseOrder(cmp));

        case KEEP:
            if (initialCapacity == 0)
                return new LinkedHashMap<>();
            else
                return new LinkedHashMap<>(initialCapacity);

        default:
        case NONE:
            if (initialCapacity == 0)
                return new HashMap<>();
            else
                return new HashMap<>(initialCapacity);
        }
    }

    public <V> Set<V> newSet() {
        return newSet(0, null);
    }

    public <V> Set<V> newSet(int initialCapacity) {
        return newSet(initialCapacity, null);
    }

    public <V> Set<V> newSet(Comparator<? super V> cmp) {
        return newSet(0, cmp);
    }

    public <V> Set<V> newSet(int initialCapacity, Comparator<? super V> cmp) {
        switch (this) {
        case SORTED:
        case ASCENDING:
            if (cmp == null)
                return new TreeSet<>();
            else
                return new TreeSet<>(cmp);

        case DESCENDING:
            if (cmp == null)
                return new TreeSet<>(Collections.reverseOrder());
            else
                return new TreeSet<>(Collections.reverseOrder(cmp));

        case KEEP:
            if (initialCapacity == 0)
                return new LinkedHashSet<>();
            else
                return new LinkedHashSet<>(initialCapacity);

        default:
        case NONE:
            if (initialCapacity == 0)
                return new HashSet<>();
            else
                return new HashSet<>(initialCapacity);
        }
    }

    public static SortOrder detect(Map<?, ?> map) {
        if (map instanceof LinkedHashMap)
            return KEEP;
        if (map instanceof SortedMap)
            return SORTED;
        return NONE;
    }

    public static SortOrder detect(Set<?> set) {
        if (set instanceof LinkedHashSet)
            return KEEP;
        if (set instanceof SortedSet)
            return SORTED;
        return NONE;
    }

}
