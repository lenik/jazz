package net.bodz.bas.t.coll;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public interface IContainer<E>
        extends
            Collection<E> {

    ContainerType getType();

    Class<? extends E> getElementType();

    //

//    boolean isKeyIndexed();
//
//    Class<?> getKeyType();
//
//    Set<?> keySet();
//
//    boolean isKeyPresent(Object key);
//
//    // E get(Object key);
//    E put(Object key, E element);
//
//    boolean addKeyed(E element, Object key);
//
//    boolean setKeyed(E element, Object key);
//
//    boolean removeKeyed(Object key);

    //

    boolean isNamed();

    Set<String> names();

    boolean isNamePresent(String name);

    E get(String name);

    E set(String name, E element);

    // E put(String name, E element);

    boolean addNamed(E element, String name);

    E removeNamed(String name);

    boolean isIndexed();

    boolean isIndexValid(int index);

    E get(int index);

    int indexOf(Object o);

    int lastIndexOf(Object o);

    void add(int index, E element);

    E set(int index, E element);

    /**
     * Removes the element at the specified position in this list (optional operation). Shifts any
     * subsequent elements to the left (subtracts one from their indices). Returns the element that
     * was removed from the list.
     *
     * @param index
     *            the index of the element to be removed
     * @return the element previously at the specified position
     * @throws UnsupportedOperationException
     *             if the <tt>remove</tt> operation is not supported
     * @throws IndexOutOfBoundsException
     *             if the index is out of range (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    E remove(int index);

    default E getFirst() {
        Iterator<E> iterator = iterator();
        if (! iterator.hasNext())
            throw new NoSuchElementException();
        E first = iterator.next();
        return first;
    }

    default E getLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return this.get(this.size() - 1);
        }
    }

    default E removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return this.remove(0);
        }
    }

    default E removeLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return this.remove(this.size() - 1);
        }
    }

    List<E> toList();

    Set<E> toSet();

    Map<String, E> toMap();

}
