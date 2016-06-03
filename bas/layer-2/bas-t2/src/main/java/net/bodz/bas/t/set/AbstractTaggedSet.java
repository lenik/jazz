package net.bodz.bas.t.set;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public abstract class AbstractTaggedSet<V>
        implements TaggedSet<V> {

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        Set<String> tags = getTags(key);
        return !tags.isEmpty();
    }

    @Override
    public void add(V key, String... tags) {
        add(key, Arrays.asList(tags));
    }

    public abstract void add(V key, Iterable<String> tags);

    @Override
    public void removeWithAllTags(String... tags) {
        for (V key : selectForAll(tags))
            remove(key);
    }

    @Override
    public void removeWithAnyTag(String... tags) {
        for (V key : selectForAny(tags))
            remove(key);
    }

    @Override
    public Collection<V> select(String tag) {
        return selectForAll(tag);
    }

}
