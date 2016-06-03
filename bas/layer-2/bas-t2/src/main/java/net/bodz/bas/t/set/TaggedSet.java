package net.bodz.bas.t.set;

import java.util.Collection;
import java.util.Set;

public interface TaggedSet<K> {

    /**
     * @return　Number　of items. (Not tags)
     */
    int size();

    boolean isEmpty();

    boolean containsKey(Object key);

    /**
     * Remove a single item.
     */
    boolean remove(K key);

    /**
     * Add an item and tag it with the specific tags.
     * 
     * If the item is already existed, the tags will be merged.
     * 
     * @param tags
     *            Can be empty.
     */
    void add(K key, String... tags);

    void add(K key, Iterable<String> tags);

    /**
     * @return Non-<code>null</code> tag set.
     */
    Set<String> getTags(Object key);

    void addTag(K key, String tag);

    void removeTag(K key, String tag);

    /**
     * Remove all items which contains all the tags.
     */
    void removeWithAllTags(String... tags);

    /**
     * Remove all items which contains any tag.
     */
    void removeWithAnyTag(String... tags);

    Collection<K> select(String tag);

    /**
     * Find items with all required tags.
     * 
     * @param tags
     *            If empty, only items without tag is selected.
     */
    Collection<K> selectForAll(String... tags);

    /**
     * Find items with any required tag.
     * 
     * @param tags
     *            Can't be empty.
     */
    Collection<K> selectForAny(String... tags);

}
