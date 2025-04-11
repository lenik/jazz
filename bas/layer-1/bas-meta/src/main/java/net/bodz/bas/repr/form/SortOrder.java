package net.bodz.bas.repr.form;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import net.bodz.bas.t.set.IdentityHashSet;

public enum SortOrder {

    NONE,

    IDENTITY,

    SORTED,

    ASCENDING,

    DESCENDING,

    KEEP,

    ;

    static final SortOrder[] values = values();

    public static SortOrder valueOf(int ordinal) {
        return values[ordinal];
    }

    public static SortOrder ofMap(Map<?, ?> map) {
        if (map == null)
            return NONE;
        if (map instanceof SortedMap<?, ?>)
            return SORTED;
        if (map instanceof LinkedHashMap<?, ?>)
            return KEEP;
        return NONE;
    }

    public <K, V> Map<K, V> newMapDefault() {
        return newMap(0, null);
    }

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

            case IDENTITY:
                if (initialCapacity == 0)
                    return new IdentityHashMap<>();
                else
                    return new IdentityHashMap<>(initialCapacity);

            case NONE:
            default:
                if (initialCapacity == 0)
                    return new HashMap<>();
                else
                    return new HashMap<>(initialCapacity);
        }
    }

    public <V> Set<V> newSetDefault() {
        return newSet(0, null);
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

            case IDENTITY:
                if (initialCapacity == 0)
                    return new IdentityHashSet<>();
                else
                    return new IdentityHashSet<>(initialCapacity);

            case NONE:
            default:
                if (initialCapacity == 0)
                    return new HashSet<>();
                else
                    return new HashSet<>(initialCapacity);
        }
    }

    public static SortOrder detect(Map<?, ?> map) {
        if (map instanceof IdentityHashMap)
            return IDENTITY;
        if (map instanceof LinkedHashMap)
            return KEEP;
        if (map instanceof SortedMap)
            return SORTED;
        return NONE;
    }

    public static SortOrder detect(Set<?> set) {
        if (set instanceof IdentityHashSet)
            return IDENTITY;
        if (set instanceof LinkedHashSet)
            return KEEP;
        if (set instanceof SortedSet)
            return SORTED;
        return NONE;
    }

}
