package net.bodz.bas.t.set;

import java.util.*;
import java.util.Map.Entry;

import net.bodz.bas.c.object.IdPool;
import net.bodz.bas.c.object.NonNullPoolIdComparator;

/**
 * Quick-Merged-Iteration Tagged Set.
 * 
 * <ol>
 * <li>The order is defined by the pool.
 * </ol>
 */
public class QmiTaggedSet<V>
        extends AbstractTaggedSet<V> {

    private IdPool pool = IdPool.getInstance();
    private Comparator<Object> cmp = new NonNullPoolIdComparator(pool);

    private Map<V, Set<String>> itemTagsMap;
    private Map<String, TreeSet<V>> tagItemsMap;

    public QmiTaggedSet() {
        itemTagsMap = new IdentityHashMap<>();
        tagItemsMap = new HashMap<>();
    }

    @Override
    public int size() {
        return itemTagsMap.size();
    }

    @Override
    public boolean contains(Object item) {
        return itemTagsMap.containsKey(item);
    }

    @Override
    public synchronized boolean remove(V item) {
        if (item == null)
            throw new NullPointerException("item");

        Set<String> tags = itemTagsMap.remove(item);
        if (tags == null)
            return false;

        for (String tag : tags) {
            Set<V> itemsWithTheTag = whoseTag(tag);
            itemsWithTheTag.remove(item);
        }
        return true;
    }

    @Override
    public synchronized void add(V item, Iterable<String> tags) {
        if (item == null)
            throw new NullPointerException("item");
        if (tags == null)
            throw new NullPointerException("tags");

        Set<String> union = getOrCreateTags(item);
        for (String tag : tags) {
            union.add(tag);
            Set<V> rindex = getOrCreateItems(tag);
            rindex.add(item);
        }
    }

    @Override
    public Set<String> getTags(Object item) {
        Set<String> tags = itemTagsMap.get(item);
        return Collections.unmodifiableSet(tags);
    }

    @Override
    public synchronized void addTag(V item, String tag) {
        if (item == null)
            throw new NullPointerException("item");
        if (tag == null)
            throw new NullPointerException("tag");
        Set<String> tags = getOrCreateTags(item);
        if (tags.add(tag)) {
            Set<V> rindex = getOrCreateItems(tag);
            rindex.add(item);
        }
    }

    @Override
    public synchronized void removeTag(V item, String tag) {
        if (item == null)
            throw new NullPointerException("item");
        if (tag == null)
            throw new NullPointerException("tag");
        Set<String> tags = getOrCreateTags(item);
        tags.remove(tag);
        Set<V> rindex = getOrCreateItems(tag);
        rindex.remove(item);
    }

    private Set<V> getOrCreateItems(String tag) {
        TreeSet<V> items = tagItemsMap.get(tag);
        if (items == null) {
            items = new TreeSet<V>(cmp);
            tagItemsMap.put(tag, items);
        }
        return items;
    }

    private Set<String> getOrCreateTags(V item) {
        Set<String> tags = itemTagsMap.get(item);
        if (tags == null) {
            tags = new HashSet<String>();
            itemTagsMap.put(item, tags);
        }
        return tags;
    }

    protected synchronized Set<V> whoseTag(String tag) {
        TreeSet<V> items = tagItemsMap.get(tag);
        if (items == null)
            return Collections.emptySet();
        else
            return Collections.unmodifiableSet(items);
    }

    public Collection<V> selectItemsWithoutTag() {
        // TODO optim... workaround..
        List<V> itemsWithoutTag = new ArrayList<>();
        for (Entry<V, Set<String>> entry : itemTagsMap.entrySet()) {
            Set<String> tags = entry.getValue();
            if (tags.isEmpty())
                itemsWithoutTag.add(entry.getKey());
        }
        return itemsWithoutTag;
    }

    @Override
    public Collection<V> selectForAll(String... tags) {
        if (tags == null)
            throw new NullPointerException("tags");

        int n = tags.length;
        if (n == 0)
            return selectItemsWithoutTag();

        @SuppressWarnings("unchecked")
        Iterator<V>[] itv = new Iterator[n];
        for (int i = 0; i < n; i++) {
            Set<V> itemsWithTheTag = whoseTag(tags[i]);
            itv[i] = itemsWithTheTag.iterator();
        }

        List<V> results = new ArrayList<V>();

        int max = -1;
        int idDups = 1;

        L: while (true) {
            for (Iterator<V> itemsForSpecificTag : itv) {
                if (!itemsForSpecificTag.hasNext())
                    break L;

                V item = itemsForSpecificTag.next();
                int id = pool.getId(item);

                while (id < max) {
                    if (!itemsForSpecificTag.hasNext())
                        break L;
                    item = itemsForSpecificTag.next();
                    id = pool.getId(item);
                }

                if (id == max)
                    idDups++;
                else
                    idDups = 1;

                if (idDups == n)
                    results.add(item);

                if (id > max)
                    max = id;
            }
        }

        return results;
    }

    @Override
    public Collection<V> selectForAny(String... tags) {
        return null;
    }

    @Override
    public String toString() {
        return itemTagsMap.keySet().toString();
    }

    public String dump() {
        StringBuilder sb = new StringBuilder();
        sb.append("Index:\n");
        for (Entry<V, Set<String>> entry : itemTagsMap.entrySet()) {
            V item = entry.getKey();
            Set<String> union = entry.getValue();
            sb.append("  ");
            sb.append(item);
            sb.append(": ");
            sb.append(union);
            sb.append('\n');
        }

        sb.append("Reversed Index:\n");
        for (Entry<String, TreeSet<V>> entry : tagItemsMap.entrySet()) {
            sb.append("  ");
            sb.append(entry.getKey());
            sb.append(": ");
            sb.append(entry.getValue());
            sb.append('\n');
        }
        return sb.toString();
    }

}
