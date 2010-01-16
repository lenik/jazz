package net.bodz.bas.collection.ints;

import java.util.Collection;

/**
 * @see Collection
 */
public interface IntCollection extends IntIterable {

    /**
     * @see Collection#size()
     */
    int size();

    /**
     * @see Collection#isEmpty()
     */
    boolean isEmpty();

    /**
     * @see Collection#contains(Object)
     */
    boolean contains(int n);

    /**
     * @see Collection#iterator()
     */
    IntIterator iterator();

    /**
     * @see Collection#toArray()
     */
    int[] toArray();

    /**
     * @see Collection#add(Object)
     */
    boolean add(int n);

    /**
     * @see Collection#remove(Object)
     */
    boolean remove(int n);

    /**
     * @see Collection#containsAll(Collection)
     */
    boolean containsAll(IntCollection c);

    /**
     * @see Collection#addAll(Collection)
     */
    boolean addAll(IntCollection c);

    /**
     * @see Collection#removeAll(Collection)
     */
    boolean removeAll(IntCollection c);

    /**
     * @see Collection#retainAll(Collection)
     */
    boolean retainAll(IntCollection c);

    /**
     * @see Collection#clear()
     */
    void clear();

}
