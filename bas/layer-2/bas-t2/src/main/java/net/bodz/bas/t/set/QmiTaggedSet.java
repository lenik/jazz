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
public class QmiTaggedSet<K>
        extends AbstractTaggedSet<K> {

    private IdPool pool = IdPool.getInstance();
    private Comparator<Object> cmp = new NonNullPoolIdComparator(pool);

    private Map<K, Set<String>> keyTagsMap;
    private Map<String, TreeSet<K>> tagKeysMap;

    public QmiTaggedSet() {
        keyTagsMap = new IdentityHashMap<>();
        tagKeysMap = new HashMap<>();
    }

    @Override
    public int size() {
        return keyTagsMap.size();
    }

    @Override
    public boolean containsKey(Object key) {
        return keyTagsMap.containsKey(key);
    }

    @Override
    public synchronized boolean remove(K key) {
        if (key == null)
            throw new NullPointerException("key");

        Set<String> tags = keyTagsMap.remove(key);
        if (tags == null)
            return false;

        for (String tag : tags) {
            Set<K> itemsWithTheTag = whoseTag(tag);
            itemsWithTheTag.remove(key);
        }
        return true;
    }

    @Override
    public synchronized void add(K key, Iterable<String> tags) {
        if (key == null)
            throw new NullPointerException("key");
        if (tags == null)
            throw new NullPointerException("tags");

        Set<String> union = getOrCreateTags(key);
        for (String tag : tags) {
            union.add(tag);
            Set<K> rindex = getOrCreateKeys(tag);
            rindex.add(key);
        }
    }

    @Override
    public Set<String> getTags(Object key) {
        Set<String> tags = keyTagsMap.get(key);
        return Collections.unmodifiableSet(tags);
    }

    @Override
    public synchronized void addTag(K key, String tag) {
        if (key == null)
            throw new NullPointerException("key");
        if (tag == null)
            throw new NullPointerException("tag");
        Set<String> tags = getOrCreateTags(key);
        if (tags.add(tag)) {
            Set<K> rindex = getOrCreateKeys(tag);
            rindex.add(key);
        }
    }

    @Override
    public synchronized void removeTag(K key, String tag) {
        if (key == null)
            throw new NullPointerException("key");
        if (tag == null)
            throw new NullPointerException("tag");
        Set<String> tags = getOrCreateTags(key);
        tags.remove(tag);
        Set<K> rindex = getOrCreateKeys(tag);
        rindex.remove(key);
    }

    private Set<K> getOrCreateKeys(String tag) {
        TreeSet<K> keys = tagKeysMap.get(tag);
        if (keys == null) {
            keys = new TreeSet<K>(cmp);
            tagKeysMap.put(tag, keys);
        }
        return keys;
    }

    private Set<String> getOrCreateTags(K key) {
        Set<String> tags = keyTagsMap.get(key);
        if (tags == null) {
            tags = new HashSet<String>();
            keyTagsMap.put(key, tags);
        }
        return tags;
    }

    protected synchronized Set<K> whoseTag(String tag) {
        TreeSet<K> keys = tagKeysMap.get(tag);
        if (keys == null)
            return Collections.emptySet();
        else
            return Collections.unmodifiableSet(keys);
    }

    public Collection<K> notTagged() {
        // TODO optim... workaround..
        List<K> list = new ArrayList<>();
        for (Entry<K, Set<String>> entry : keyTagsMap.entrySet()) {
            Set<String> tags = entry.getValue();
            if (tags.isEmpty())
                list.add(entry.getKey());
        }
        return list;
    }

    @Override
    public Collection<K> selectForAll(String... tags) {
        if (tags == null)
            throw new NullPointerException("tags");

        int n = tags.length;
        if (n == 0)
            return notTagged();

        @SuppressWarnings("unchecked")
        Iterator<K>[] itv = new Iterator[n];
        for (int i = 0; i < n; i++) {
            Set<K> itemsWithTheTag = whoseTag(tags[i]);
            itv[i] = itemsWithTheTag.iterator();
        }

        List<K> results = new ArrayList<K>();

        int max = -1;
        int idDups = 1;

        L: while (true) {
            for (Iterator<K> itemsForSpecificTag : itv) {
                if (!itemsForSpecificTag.hasNext())
                    break L;

                K key = itemsForSpecificTag.next();
                int id = pool.getId(key);

                while (id < max) {
                    if (!itemsForSpecificTag.hasNext())
                        break L;
                    key = itemsForSpecificTag.next();
                    id = pool.getId(key);
                }

                if (id == max)
                    idDups++;
                else
                    idDups = 1;

                if (idDups == n)
                    results.add(key);

                if (id > max)
                    max = id;
            }
        }

        return results;
    }

    @Override
    public Collection<K> selectForAny(String... tags) {
        return null;
    }

    @Override
    public String toString() {
        return keyTagsMap.keySet().toString();
    }

    public String dump() {
        StringBuilder sb = new StringBuilder();
        sb.append("Index:\n");
        for (Entry<K, Set<String>> entry : keyTagsMap.entrySet()) {
            K key = entry.getKey();
            Set<String> union = entry.getValue();
            sb.append("  ");
            sb.append(key);
            sb.append(": ");
            sb.append(union);
            sb.append('\n');
        }

        sb.append("Reversed Index:\n");
        for (Entry<String, TreeSet<K>> entry : tagKeysMap.entrySet()) {
            sb.append("  ");
            sb.append(entry.getKey());
            sb.append(": ");
            sb.append(entry.getValue());
            sb.append('\n');
        }
        return sb.toString();
    }

}
