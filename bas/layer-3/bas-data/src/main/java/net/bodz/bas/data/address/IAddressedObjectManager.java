package net.bodz.bas.data.address;

/**
 * Overlapping is not allowed.
 */
public interface IAddressedObjectManager<T extends IAddressed> {

    /**
     * Add an addressed object.
     * 
     * @return <code>null</code> if <code>obj</code> is successfully added. Otherwise, existing
     *         object whose address range is overlapped is returned.
     * @throws IllegalArgumentException
     *             If the size of the given <code>obj</code> is <code>0</code>.
     */
    T add(T obj);

    /**
     * Remove the specified object from this manager.
     * 
     * Only the same object which is added previously with the same address, can be removed.
     * 
     * @return <code>true</code> if <code>obj</code> is successfully removed. If the
     *         <code>obj</code> wasn't previously added, <code>false</code> is returned.
     */
    boolean remove(T obj);

    T get(int address);

    /**
     * Find object whose address range includes the specified address.
     * 
     * @return <code>null</code> if no such object found.
     */
    T find(int address);

    /**
     * Find the most nearby object whose end address is before the specified address.
     * 
     * @return <code>null</code> if there is no object addressed before the given address.
     */
    T findBefore(int address);

    /**
     * Find the most nearby object whose start address is after the specified address.
     * 
     * @return <code>null</code> if there is no object addressed after the given address.
     */
    T findAfter(int address);

}
