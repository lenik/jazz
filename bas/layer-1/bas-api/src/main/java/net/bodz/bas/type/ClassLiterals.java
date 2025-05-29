package net.bodz.bas.type;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

@SuppressWarnings("unchecked")
public interface ClassLiterals {

    Class<Collection<?>> Collection_class = (Class<Collection<?>>) (Class<?>) Collection.class;
    Class<List<?>> List_class = (Class<List<?>>) (Class<?>) List.class;
    Class<Set<?>> Set_class = (Class<Set<?>>) (Class<?>) Set.class;
    Class<SortedSet<?>> SortedSet_class = (Class<SortedSet<?>>) (Class<?>) SortedSet.class;

    Class<Map<?, ?>> Map_class = (Class<Map<?, ?>>) (Class<?>) Map.class;
    Class<SortedMap<?, ?>> SortedMap_class = (Class<SortedMap<?, ?>>) (Class<?>) SortedMap.class;
    Class<NavigableMap<?, ?>> NavigableMap_class = (Class<NavigableMap<?, ?>>) (Class<?>) NavigableMap.class;

}
