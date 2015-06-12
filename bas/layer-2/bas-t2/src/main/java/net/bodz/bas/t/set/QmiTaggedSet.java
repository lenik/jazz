package net.bodz.bas.t.set;

import java.util.*;
import java.util.Map.Entry;

import net.bodz.bas.c.object.IdPool;
import net.bodz.bas.c.object.NonNullPoolIdComparator;

/**
 * Quick-Merged-Iteration Tagged Set.
 */
public class QmiTaggedSet<V>
        extends AbstractTaggedSet<V> {

    private IdPool pool = IdPool.getInstance();
    private Comparator<Object> cmp = new NonNullPoolIdComparator(pool);

    private Map<String, TreeSet<V>> rindex;
    private Map<V, String[]> itemTagsMap;

    public QmiTaggedSet() {
        rindex = new HashMap<>();
        itemTagsMap = new IdentityHashMap<>();
    }

    protected synchronized TreeSet<V> getSetForTag(String tag) {
        TreeSet<V> set = rindex.get(tag);
        if (set == null) {
            set = new TreeSet<V>(cmp);
            rindex.put(tag, set);
        }
        return set;
    }

    @Override
    public synchronized void add(V item, String... tags) {
        if (item == null)
            throw new NullPointerException("item");
        if (tags == null)
            throw new NullPointerException("tags");

        String[] oldTags = itemTagsMap.get(item);
        if (oldTags != null) {
            if (Arrays.equals(oldTags, tags))
                return;
            else
                remove(item);
        }

        for (String tag : tags) {
            TreeSet<V> set = getSetForTag(tag);
            set.add(item);
        }
        itemTagsMap.put(item, tags);
    }

    @Override
    public synchronized boolean remove(V item) {
        if (item == null)
            throw new NullPointerException("item");

        String[] tags = itemTagsMap.remove(item);
        if (tags == null)
            return false;

        for (String tag : tags) {
            TreeSet<V> set = getSetForTag(tag);
            set.remove(item);
        }
        return true;
    }

    @Override
    public void remove(String... tags) {
        for (V item : select(tags))
            remove(item);
    }

    @Override
    public Collection<V> select(String... tags) {
        if (tags == null)
            throw new NullPointerException("tags");

        int n = tags.length;
        if (n == 0) {
            // TODO optim... workaround..
            List<V> defaults = new ArrayList<>();
            for (Entry<V, String[]> entry : itemTagsMap.entrySet()) {
                String[] tv = entry.getValue();
                if (tv.length == 0)
                    defaults.add(entry.getKey());
            }
            return defaults;
        }

        @SuppressWarnings("unchecked")
        Iterator<V>[] itv = new Iterator[n];
        for (int i = 0; i < n; i++) {
            TreeSet<V> set = getSetForTag(tags[i]);
            itv[i] = set.iterator();
        }

        List<V> results = new ArrayList<V>();

        int max = -1;
        int idDups = 1;

        L: while (true) {
            for (Iterator<V> it : itv) {
                if (!it.hasNext())
                    break L;

                V item = it.next();
                int id = pool.getId(item);

                while (id < max) {
                    if (!it.hasNext())
                        break L;
                    item = it.next();
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
    public int size() {
        return itemTagsMap.size();
    }

    @Override
    public String toString() {
        return itemTagsMap.keySet().toString();
    }

    public String dump() {
        StringBuilder sb = new StringBuilder();
        sb.append("Index:\n");
        for (Entry<V, String[]> entry : itemTagsMap.entrySet()) {
            V item = entry.getKey();
            String[] tags = entry.getValue();
            sb.append("  ");
            sb.append(item);
            sb.append(": ");
            sb.append(Arrays.asList(tags));
            sb.append('\n');
        }

        sb.append("Reversed Index:\n");
        for (Entry<String, TreeSet<V>> entry : rindex.entrySet()) {
            sb.append("  ");
            sb.append(entry.getKey());
            sb.append(": ");
            sb.append(entry.getValue());
            sb.append('\n');
        }
        return sb.toString();
    }

}
