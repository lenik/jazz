package net.bodz.bas.potato.ref;

public interface IRefEntries {

    /**
     * Get the number of ref entries in the composite.
     */
    int getRefEntryCount();

    /**
     * Get ref entry by index.
     * 
     * @throws IndexOutOfBoundsException
     *             If index is out of bounds.
     */
    IRefEntry<?> getRefEntry(int index);

    /**
     * @param name
     *            Non-<code>null</code> entry name.
     * @return <code>null</code> if the named entry isn't defined.
     */
    IRefEntry<?> getRefEntry(String name);

    Iterable<? extends IRefEntry<?>> getRefEntries();

}
