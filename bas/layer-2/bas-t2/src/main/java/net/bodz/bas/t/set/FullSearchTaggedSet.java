package net.bodz.bas.t.set;

import java.util.*;
import java.util.Map.Entry;

public class FullSearchTaggedSet<V>
        extends AbstractTaggedSet<V> {

    Map<V, Set<String>> map;

    public FullSearchTaggedSet() {
        map = new IdentityHashMap<>();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean remove(V item) {
        Set<String> tags = map.remove(item);
        return tags != null;
    }

    @Override
    public Set<String> getTags(Object item) {
        return map.get(item);
    }

    public Set<String> getOrCreateTags(V item) {
        Set<String> tags = map.get(item);
        if (tags == null) {
            tags = new TreeSet<String>();
            map.put(item, tags);
        }
        return tags;
    }

    @Override
    public void addTag(V item, String tag) {
        getOrCreateTags(item).add(tag);
    }

    @Override
    public void removeTag(V item, String tag) {
        getOrCreateTags(item).remove(tag);
    }

    @Override
    public Collection<V> selectForAll(String... qtags) {
        if (qtags.length == 0)
            return map.keySet();

        List<String> q = Arrays.asList(qtags);
        List<V> ans = new ArrayList<>();
        for (Entry<V, Set<String>> entry : map.entrySet()) {
            Set<String> tags = entry.getValue();
            if (tags.containsAll(q))
                ans.add(entry.getKey());
        }
        return ans;
    }

    @Override
    public Collection<V> selectForAny(String... qtags) {
        if (qtags.length == 0)
            return map.keySet();

        List<String> q = Arrays.asList(qtags);
        List<V> ans = new ArrayList<>();
        for (Entry<V, Set<String>> entry : map.entrySet()) {
            Set<String> tags = entry.getValue();
            if (containsAny(tags, q))
                ans.add(entry.getKey());
        }
        return ans;
    }

    static boolean containsAny(Collection<?> av, Collection<?> bv) {
        for (Object b : bv)
            if (av.contains(b))
                return true;
        return false;
    }

    @Override
    public void add(V item, Iterable<String> newTags) {
        Set<String> tags = getOrCreateTags(item);
        for (String tag : newTags)
            tags.add(tag);
    }

}
