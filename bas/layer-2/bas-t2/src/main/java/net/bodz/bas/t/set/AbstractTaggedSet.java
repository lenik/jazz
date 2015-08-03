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
    public boolean contains(Object item) {
        Set<String> tags = getTags(item);
        return !tags.isEmpty();
    }

    @Override
    public void add(V item, String... tags) {
        add(item, Arrays.asList(tags));
    }

    public abstract void add(V item, Collection<String> tags);

    @Override
    public void removeWithAllTags(String... tags) {
        for (V item : selectForAll(tags))
            remove(item);
    }

    @Override
    public void removeWithAnyTag(String... tags) {
        for (V item : selectForAny(tags))
            remove(item);
    }

    @Override
    public Collection<V> select(String tag) {
        return selectForAll(tag);
    }

}
