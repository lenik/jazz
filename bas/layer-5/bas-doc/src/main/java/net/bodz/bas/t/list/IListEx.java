package net.bodz.bas.t.list;

import java.util.List;
import java.util.function.Supplier;

public interface IListEx<E>
        extends
            List<E> {

    E getFirst();

    E getLast();

    /**
     * @param retainIndex
     *            ignored if out of bounds.
     * @return the element at retainIndex, <code>null</code> if the index is out of bounds.
     */
    E clearExcept(int retainIndex);

    /**
     * @param retainStart
     *            ignored if out of bounds.
     * @param retainEnd
     *            ignored if out of bounds.
     * @return <code>true</code> if any change has been made.
     */
    boolean clearExcept(int retainStart, int retainEnd);

    /**
     * @param retainIndex
     *            ignored if out of bounds.
     * @param defaultItemFactory
     *            used to create a default item if the list goes empty.
     * @return the element at retainIndex, <code>null</code> if the index is out of bounds and no
     *         factory specified.
     */
    E clearExcept(int retainIndex, Supplier<E> defaultItemFactory);

    <item_t extends E> item_t prepend(item_t item);

    <item_t extends E> item_t append(item_t item);

    <item_t extends E> item_t insert(int index, item_t item);

}
