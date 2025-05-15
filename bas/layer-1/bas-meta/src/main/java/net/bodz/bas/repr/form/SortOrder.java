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

import net.bodz.bas.meta.decl.NotNull;
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

    @SuppressWarnings("unchecked")
    public <K, V> Map<K, V> newMapDefault() {
        return (Map<K, V>) newMap(0);
    }

    public <K extends Comparable<K>, V> Map<K, V> newMap() {
        return newMap(0);
    }

    public <K, V> Map<K, V> newMap(Comparator<? super K> cmp) {
        return newMap(0, cmp);
    }

    public <K extends Comparable<K>, V> Map<K, V> newMap(int initialCapacity) {
        switch (this) {
            case SORTED:
            case ASCENDING:
                return new TreeMap<>();

            case DESCENDING:
                return new TreeMap<>(Collections.reverseOrder());

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

    public <K, V> Map<K, V> newMap(int initialCapacity, @NotNull Comparator<? super K> cmp) {
        switch (this) {
            case SORTED:
            case ASCENDING:
                return new TreeMap<>(cmp);

            case DESCENDING:
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

    @SuppressWarnings("unchecked")
    public <E> Set<E> newSetDefault() {
        return (Set<E>) newSet(0);
    }

    public <E extends Comparable<E>> Set<E> newSet() {
        return newSet(0);
    }

    public <E> Set<E> newSet(Comparator<? super E> cmp) {
        return newSet(0, cmp);
    }

    public <E extends Comparable<E>> Set<E> newSet(int initialCapacity) {
        switch (this) {
            case SORTED:
            case ASCENDING:
                return new TreeSet<>();

            case DESCENDING:
                return new TreeSet<>(Collections.reverseOrder());

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

    public <E> Set<E> newSet(int initialCapacity, Comparator<? super E> cmp) {
        switch (this) {
            case SORTED:
            case ASCENDING:
                return new TreeSet<>(cmp);

            case DESCENDING:
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
