package net.bodz.bas.t.set;

import java.util.Collection;
import java.util.Set;

/**
 * @see Lucene
 */
public interface TaggedSet<V> {

    /**
     * @return　Number　of items. (Not tags)
     */
    int size();

    boolean isEmpty();

    boolean contains(Object item);

    /**
     * Remove a single item.
     */
    boolean remove(V item);

    /**
     * Add an item and tag it with the specific tags.
     * 
     * If the item is already existed, the tags will be merged.
     * 
     * @param tags
     *            Can be empty.
     */
    void add(V item, String... tags);

    /**
     * @return Non-<code>null</code> tag set.
     */
    Set<String> getTags(Object item);

    void addTag(V item, String tag);

    void removeTag(V item, String tag);

    /**
     * Remove all items which contains all the tags.
     */
    void removeWithAllTags(String... tags);

    /**
     * Remove all items which contains any tag.
     */
    void removeWithAnyTag(String... tags);

    Collection<V> select(String tag);

    /**
     * Find items with all required tags.
     * 
     * @param tags
     *            If empty, only items without tag is selected.
     */
    Collection<V> selectForAll(String... tags);

    /**
     * Find items with any required tag.
     * 
     * @param tags
     *            Can't be empty.
     */
    Collection<V> selectForAny(String... tags);

}
