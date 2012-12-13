package net.bodz.bas.rtx;

public interface IQueryable {

    /**
     * If specification is String, this function should return the same result as
     * {@link #query(String)}.
     * <p>
     * If specification is Class, this function should return the same result as
     * {@link #query(Class)}.
     * 
     * @param specification
     *            Non-<code>null</code> specification variable in any type.
     * @return <code>null</code> If no available implementation exists.
     * @throws NullPointerException
     *             If <code>specificationType</code> is <code>null</code>.
     * @throws QueryException
     *             If query is failed.
     */
    Object query(Object specification)
            throws QueryException;

    /**
     * @return <code>null</code> If no available implementation exists for the
     *         <code>specificationId</code>.
     * @throws NullPointerException
     *             If <code>specificationId</code> is <code>null</code>.
     * @throws QueryException
     *             If query is failed.
     */
    Object query(String specificationId)
            throws QueryException;

    /**
     * @param specificationType
     *            The specification type to query about, the implementation should not consider any
     *            super or derived class or interface of the specified
     *            <code>specificationType</code> , but one may return the result which is instance
     *            of <code>specificationType</code>. Return <code>null</code> if the specification
     *            type doesn't available.
     * @return <code>null</code> If no available implementation exists for the
     *         <code>specificationType</code>.
     * @throws NullPointerException
     *             If <code>specificationType</code> is <code>null</code>.
     * @throws ClassCastException
     *             If the returned implementation can't be isn't instanceof specifiec
     *             <code>specificationType</code>.
     * @throws QueryException
     *             If query is failed.
     */
    <T> T query(Class<T> specificationType)
            throws QueryException;

}
