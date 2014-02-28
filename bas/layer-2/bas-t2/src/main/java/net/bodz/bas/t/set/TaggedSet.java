package net.bodz.bas.t.set;

import java.util.Collection;

/**
 * @see Lucene
 */
public interface TaggedSet<V> {

    /**
     * Add an item and tagging with the specific tags.
     */
    void add(V item, String... tags);

    /**
     * Remove a single item.
     */
    boolean remove(V item);

    /**
     * Remove all items with the specific tags.
     */
    void remove(String... tags);

    /**
     * List items with the specific tags.
     */
    Collection<V> select(String... tags);

    int size();

    boolean isEmpty();

}
